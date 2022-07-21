package 자바공부.모던자바;

import java.io.File;
import java.io.FileFilter;

public class Chapter1 {

    public static void main(String[] args) {

        // 익명 클래스를 통한 파일 리스팅
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        // 메서드 참조를 이용한 파일리스팅
        File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);
    }
}
