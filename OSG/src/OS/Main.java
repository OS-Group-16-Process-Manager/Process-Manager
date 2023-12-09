package OS;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {
    private DefaultTableModel tableModel;

    public Main() {
        setTitle("Process Manager");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        // Update process details every 2 seconds (2000 milliseconds)
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                retrieveProcessDetails();
            }
        }, 0, 2000);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        JTable processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        JPanel buttonPanel = new JPanel();

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
        // Simulating process details with random/fake data
        Random random = new Random();
        ProcessDetails[] fakeProcessDetails = new ProcessDetails[10]; // Generating 10 fake processes

        for (int i = 0; i < 10; i++) {
            long pid = i + 1;
            String name = "Process " + pid;
            double cpuUsage = random.nextDouble() * 100; // Random CPU usage between 0 and 100
            long memoryUsage = random.nextLong() & Long.MAX_VALUE; // Random positive long number
            String boundType = determineBoundType(cpuUsage);

            fakeProcessDetails[i] = new ProcessDetails(pid, name, cpuUsage, memoryUsage, boundType);
        }

        return fakeProcessDetails;
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