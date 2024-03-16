package LCR.string;

public class Question {

    /**
     * LCR 122. 路径加密
     *
     * @param path
     * @return
     */
    public String pathEncryption(String path) {
        return path.replace(".","%20");
    }
}
