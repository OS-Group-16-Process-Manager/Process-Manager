package OS;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;
import java.util.stream.*;

public class Main extends JFrame {
    private DefaultTableModel tableModel;

    public Main() {
        setTitle("Process Manager");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        JTable processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        JButton retrieveProcessDetailsBtn = new JButton("Retrieve Process Details");
        retrieveProcessDetailsBtn.addActionListener(e -> retrieveProcessDetails());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(retrieveProcessDetailsBtn);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void retrieveProcessDetails() {
        Object[] columns = {"PID", "Name", "CPU Usage (%)", "Memory Usage (bytes)", "Bound Type"};
        tableModel.setColumnIdentifiers(columns);

        ProcessDetails[] processDetails = getProcessDetails();
        displayProcessDetails(processDetails);
    }

    private ProcessDetails[] getProcessDetails() {
        return ProcessHandle.allProcesses()
                .map(handle -> {
                    long pid = handle.pid();
                    String name = handle.info().command().orElse("N/A");
                    double cpuUsage = getCPUUsage(handle);
                    long memoryUsage = getMemoryUsage(handle);
                    String boundType = determineBoundType(cpuUsage);

                    return new ProcessDetails(pid, name, cpuUsage, memoryUsage, boundType);
                })
                .toArray(ProcessDetails[]::new);
    }

    private double getCPUUsage(ProcessHandle handle) {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        double cpuUsage = osBean.getSystemLoadAverage();
        return cpuUsage;
    }

    private long getMemoryUsage(ProcessHandle handle) {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        return totalMemory - freeMemory;
    }

    private String determineBoundType(double cpuUsage) {
        if (cpuUsage < 30) {
            return "I/O Bound";
        } else {
            return "CPU Bound";
        }
    }

    private void displayProcessDetails(ProcessDetails[] processDetails) {
        tableModel.setRowCount(0); // Clear previous rows

        for (ProcessDetails process : processDetails) {
            Object[] rowData = {process.getPid(), process.getName(), process.getCpuUsage(), process.getMemoryUsage(), process.getBoundType()};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main managerUI = new Main();
            managerUI.setVisible(true);
        });
    }
}

class ProcessDetails {
    private long pid;
    private String name;
    private double cpuUsage;
    private long memoryUsage;
    private String boundType;

    public ProcessDetails(long pid, String name, double cpuUsage, long memoryUsage, String boundType) {
        this.pid = pid;
        this.name = name;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.boundType = boundType;
    }

    public long getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public long getMemoryUsage() {
        return memoryUsage;
    }

    public String getBoundType() {
        return boundType;
    }
}