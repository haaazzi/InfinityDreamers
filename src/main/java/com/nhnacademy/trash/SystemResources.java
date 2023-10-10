<<<<<<< HEAD
// package com.nhnacademy.trash;
=======
package com.nhnacademy.trash;
// package com.nhnacademy.server;
>>>>>>> 46f4d98 (폴더 수정)

// import java.io.File;
// import java.lang.management.ManagementFactory;

// public class SystemResources {

// static double kb = 1024.0;
// static double mb = 1024.0 * 1024.0;
// static double gb = 1024.0 * 1024.0 * 1024;

// public SystemResources() {
// showDisk();
// showCPU();
// systemMemory();
// }

// public static void showDisk() {
// File root = null;
// try {
// root = new File("/");
// System.out.println("Total Space: " + (int) (root.getTotalSpace() / gb) +
// "GB");
// System.out.println("Usable Space: " + (int) (root.getUsableSpace() / gb) +
// "GB");

// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// public void showCPU() {

// final com.sun.management.OperatingSystemMXBean osBean =
// (com.sun.management.OperatingSystemMXBean) ManagementFactory
// .getOperatingSystemMXBean();
// double load;
// while (true) {
// load = osBean.getSystemCpuLoad();
// if (load != 0) {
// break;
// }
// try {
// Thread.sleep(1000);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// System.out.println("CPU Usage : " + load * 100.0 + "%");
// }

// public void systemMemory() {
// Runtime runtime = Runtime.getRuntime();
// int total = (int) (runtime.totalMemory() / mb);
// int free = (int) (runtime.freeMemory() / mb);
// int used = total - free;

// System.out.println("전체 메모리" + total + "MB");
// System.out.println("사용중인 메모리" + free + "MB");
// System.out.println("사용가능한 메모리" + used + "MB");

// }

// public static void main(String[] args) {
// SystemResources s = new SystemResources();
// }
// }
