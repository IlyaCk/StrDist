import java.io.IOException;
import java.nio.file.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String subStr = Files.readString(Path.of("sub.txt")).replaceAll("[\\r\\n]+", " ");
        String superStr = Files.readString(Path.of("super.txt"));
        com.example.demo.utils.StrDist.DistResInfo d = com.example.demo.utils.StrDist.calcStrDist(subStr, superStr, true, true);
        Files.writeString(Path.of("cmp.html"), d.diffAsHtml);
        System.out.println(d.subStrMarksPlusesAndMinuses);
        System.out.println(subStr);
    }

//    private static String buildHtmlWayTwo(Set<Integer> posMapped, String str) {
//        StringBuilder sb = new StringBuilder("<p>\n");
//        for (int i = 0; i < str.length();) {
//            if(posMapped.contains(i)) {
//                sb.append("<span class=\"goo\">");
//                while(i < str.length() && posMapped.contains(i)) {
//                    sb.append(str.charAt(i));
//                    i++;
//                }
//                sb.append("</span>");
//            } else {
//                sb.append(str.charAt(i));
//                i++;
//            }
//        }
//        return sb.toString();
//    }
//
//    /**
//     *
//     * @param posMapped result of calcStrDist
//     * @param subStr substring tested by calcStrDist
//     * @param superStr superstring tested by calcStrDist
//     * @return html-presentation of compare. Includes both inserted and skipped.
//     */
//    private static String buildHtmlWayThree(NavigableMap<Integer, Integer> posMapped, String subStr, String superStr) throws IOException {
//        StringBuilder sb = new StringBuilder("<p>\n");
//        for(int i = 0; i < subStr.length(); ) {
//            if(posMapped.containsKey(i)) {
//                sb.append("<span class=\"goo\">");
//                while(i < subStr.length() && posMapped.containsKey(i)) {
//                    sb.append(subStr.charAt(i));
//                    i++;
//                    if(posMapped.containsKey(i) && posMapped.get(i) > posMapped.get(i-1) + 1) {
//                        sb.append("</span>");
//                        sb.append("<span class=\"skipped\">");
//                        for(int j=posMapped.get(i-1) + 1; j < posMapped.get(i); j++) {
//                            sb.append(superStr.charAt(j));
//                        }
//                        break;
//                    }
//                }
//                sb.append("</span>");
//            } else {
//                sb.append("<span class=\"baaa\">");
//                while(i < subStr.length() && !(posMapped.containsKey(i))) {
//                    sb.append(subStr.charAt(i));
//                    i++;
//                }
//                if(i < subStr.length()) {
//                    int jStart = posMapped.lowerEntry(i).getValue();
//                    int jEnd = posMapped.ceilingEntry(i).getValue();
//                    if(jEnd - jStart > 1) {
//                        sb.append("</span>");
//                        sb.append("<span class=\"skipped\">");
//                        for(int j = jStart+1; j < jEnd; j++) {
//                            sb.append(superStr.charAt(j));
//                        }
//                    }
//                }
//                sb.append("</span>");
//            }
//        }
//        sb.append("\n</p>\n");
//        sb.append(Files.readString(Path.of("a.css")));
//        return sb.toString();
//    }
//
//    private static String buildHtml(List<Integer> posDiffers, String str, Set<Integer> posMapped, int dist, boolean smallBeginAndEnd) throws IOException {
//        Set<Integer> posDifferSet = new HashSet<>(posDiffers);
//        StringBuilder sb = new StringBuilder("<html>\n");
//        sb.append(buildHtmlWayOne(posDifferSet, str, smallBeginAndEnd));
//        sb.append("\n<br>\n");
//        sb.append(buildHtmlWayTwo(posDifferSet, str));
//        sb.append("\n<br>\n").append("<p>dist=").append(dist).append("</p>\n<br>\n");
//        sb.append(Files.readString(Path.of("a.css")));
//        sb.append("</html>");
//        return sb.toString();
//    }
//
//    private static String buildHtmlWayOne(Set<Integer> posDifferSet, String str, boolean smallBeginAndEnd) {
//        StringBuilder sb = new StringBuilder("<p>\n");
//        for (int i = 0; i < str.length();) {
//            if (posDifferSet.contains(i)) {
//                sb.append("<span class=\"ba\">");
//                while(i < str.length() && posDifferSet.contains(i)) {
//                    sb.append(str.charAt(i));
//                    i++;
//                }
//                sb.append("</span>");
//            } else {
//                sb.append("<span class=\"goo\">");
//                while(i < str.length() && !(posDifferSet.contains(i))) {
//                    sb.append(str.charAt(i));
//                    i++;
//                }
//                sb.append("</span>");
//            }
//        }
//        sb.append("\n</p>\n");
//        if (smallBeginAndEnd) {
//            int i1 = sb.indexOf("ba");
//            sb.insert(i1+2, "aa");
//            int i2 = sb.lastIndexOf("ba");
//            if(i2 > i1) {
//                sb.insert(i2+2, "aa");
//            }
//        }
//        return sb.toString();
//    }
}