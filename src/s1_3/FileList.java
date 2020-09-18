/**
 * @FileName: FileList.java
 * @Author: zzc
 * @Date: 2020年09月18日 20:23:30
 * @Version V1.0.0
 */

package s1_3;

import java.io.File;

public class FileList {
    // P106 1.3.43
    private ArrayQueue<File> queue = new ArrayQueue<>();
    private static final String directory = "E:/CLion 2020.1/";

    private void printFileList() {
        queue.enqueue(new File(directory));

        while (!queue.isEmpty()) {
            File file = queue.dequeue();
            if (file.isDirectory()) {
                File[] dirs = file.listFiles();
                if (dirs == null) continue;
                for (File subDirectory : dirs) {
                    queue.enqueue(subDirectory);
                }
            } else {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) {
        new FileList().printFileList();
    }
}
