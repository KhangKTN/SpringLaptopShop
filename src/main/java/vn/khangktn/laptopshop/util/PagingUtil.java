package vn.khangktn.laptopshop.util;

public class PagingUtil {
    public static int getPageNumberFromString(String pageString) {
        int pageNumber;

        if(pageString == null) {
            pageNumber = 0;
        } else if("1".equals(pageString)) {
            pageNumber = 1;
        } else {
            try {
                pageNumber = Integer.parseInt(pageString);
            } catch (Exception e) {
                e.printStackTrace();
                pageNumber = 1;
            }
        }
        
        return pageNumber;
    }
}
