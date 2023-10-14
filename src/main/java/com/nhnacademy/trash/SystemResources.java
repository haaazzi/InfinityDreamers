package com.nhnacademy.trash;

import java.io.File;
import java.lang.management.ManagementFactory;

public class SystemResources {

    static double kb = 1024.0;
    static double mb = 1024.0 * 1024.0;
    static double gb = 1024.0 * 1024.0 * 1024;

    public SystemResources() {
        showDisk();
        showCPU();
        systemMemory();
    }

    public String getResources(){
        StringBuilder result = new StringBuilder();
        result.append(showCPU());
        result.append(showDisk());
        result.append(systemMemory());
        return result.toString();
    }

    public static String showDisk() {
        String result = "";
        File root = null;
        try {
            root = new File("/");
            result += "Total Space: " + (int) (root.getTotalSpace() / gb) +
                    "GB\n";
            result += "Usable Space: " + (int) (root.getUsableSpace() / gb) +
                    "GB\n";
            // System.out.println("Total Space: " + (int) (root.getTotalSpace() / gb) +
            //         "GB");
            // System.out.println("Usable Space: " + (int) (root.getUsableSpace() / gb) +
            //         "GB");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String showCPU() {
        final com.sun.management.OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        double load;
        while (true) {
            load = osBean.getSystemCpuLoad();
            if (load != 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "CPU Usage : " + load * 100.0 + "%\n";
    }

    public String systemMemory() {
        String result = "";
        Runtime runtime = Runtime.getRuntime();
        int total = (int) (runtime.totalMemory() / mb);
        int free = (int) (runtime.freeMemory() / mb);
        int used = total - free;

        result += "전체 메모리" + total + "MB\n";
        result += "사용중인 메모리" + free + "MB\n";
        result += "사용가능한 메모리" + used + "MB\n";
        // System.out.println("전체 메모리" + total + "MB");
        // System.out.println("사용중인 메모리" + free + "MB");
        // System.out.println("사용가능한 메모리" + used + "MB");
        return result;
    }

    public static void main(String[] args) {
        SystemResources s = new SystemResources();
    }
}
