import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Main {
    public static String cc(String subStr, String superStr, StrDist.SearchBorder left, StrDist.SearchBorder right, boolean doSubtractIfLongSameSeq) {
        long start = System.nanoTime();
        String res = StrDist.calcStrDist(subStr, superStr, left, right, true, doSubtractIfLongSameSeq).diffAsHtml;
        long end = System.nanoTime();
        return res.replace("<html>", "<td>")
                .replace("</html>", "</td>")
                .replace("(dist", "(time = " + ((end-start)/(1000*1000)) + "ms; dist");
    }

    public static String sameBordersDifferentOptions(String subStr, String superStr, StrDist.SearchBorder left, StrDist.SearchBorder right) {
        return "<tr>\n<td>\nleft=" + left + "\nright=" + right + "\n</td>\n" +
                cc(subStr, superStr, left, right, false) +
                cc(subStr, superStr, left, right, true) +
                cc(subStr.toUpperCase(Locale.ROOT), superStr.toUpperCase(Locale.ROOT), left, right, false) +
                cc(subStr.toUpperCase(Locale.ROOT), superStr.toUpperCase(Locale.ROOT), left, right, true) +
                "</tr>\n";
    }

    public static void main(String[] args) throws IOException {
        String subStr = Files.readString(Path.of("sub.txt"));
        String superStr = Files.readString(Path.of("super.txt"));
        StringBuilder sb = new StringBuilder("<html>\n<table border=2px>\n");
        sb.append(sameBordersDifferentOptions(subStr, superStr, StrDist.SearchBorder.WHOLE_TEXT,    StrDist.SearchBorder.WHOLE_TEXT ));
        sb.append(sameBordersDifferentOptions(subStr, superStr, StrDist.SearchBorder.ROW,           StrDist.SearchBorder.ROW        ));
        sb.append(sameBordersDifferentOptions(subStr, superStr, StrDist.SearchBorder.WORD,          StrDist.SearchBorder.ROW        ));
        sb.append(sameBordersDifferentOptions(subStr, superStr, StrDist.SearchBorder.ROW,           StrDist.SearchBorder.WORD       ));
        sb.append(sameBordersDifferentOptions(subStr, superStr, StrDist.SearchBorder.WORD,          StrDist.SearchBorder.WORD       ));
        sb.append(sameBordersDifferentOptions(subStr, superStr, StrDist.SearchBorder.ANYWHERE,      StrDist.SearchBorder.ANYWHERE   ));
        sb.append("</table>\n");
        sb.append("<style>\n\t.good {\n\t\tcolor: green;\n\t\tfont-weight: bold;\n\t}\n</style>\n");
        sb.append("<style>\n\t.skip {\n\t\tcolor: orange;\n\t\ttext-decoration: underline;\n\t}\n</style>\n");
        sb.append("<style>\n\t.ins {\n\t\tcolor: red;\n\t\ttext-decoration: line-through;\n\t}\n</style>\n</html>");
        Files.writeString(Path.of("cmp.html"), sb.toString());
    }
}
