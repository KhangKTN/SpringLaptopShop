package vn.khangktn.laptopshop.constant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProductSelectConstant {
    public static final Map<String, String> cpuList = new TreeMap<>(){{
        put("intel-core-i9", "Intel Core i9");
        put("intel-core-i7", "Intel Core i7");
        put("intel-core-i5", "Intel Core i5");
        put("intel-core-i3", "Intel Core i3");
        put("apple-m1", "Apple M1");
        put("apple-m2", "Apple M2");
        put("apple-m3", "Apple M3");
        put("apple-m4", "Apple M4");
        put("amd-ryzen-9", "AMD Ryzen 9");
        put("amd-ryzen-7", "AMD Ryzen 7");
        put("amd-ryzen-5", "AMD Ryzen 5");
        put("amd-ryzen-3", "AMD Ryzen 3");
    }};

    public static final Map<String, String> vgaList = new LinkedHashMap<>(){{
        put("nvidia-geforce-series", "NVIDIA GeForce");
        put("nvidia-geforce-mx-series", "NVIDIA GeForce MX");
        put("intel-gpu", "Intel GPU");
        put("amd-radeon", "AMD Radeon");
        put("apple-gpu", "Apple GPU");
    }};

    public static final Map<String, String> storageList = new LinkedHashMap<>(){{
        put("1-tb", "SSD 1 TB");
        put("512-gb", "SSD 512 GB");
        put("256-gb", "SSD 256 GB");
        put("128-gb", "SSD 128 GB");
    }};

    public static final Map<String, String> ramList = new LinkedHashMap<>(){{
        put("64-gb", "64 TB");
        put("32-gb", "32 GB");
        put("16-gb", "16 GB");
        put("8-gb", "8 GB");
    }};

    public static final Map<String, String> factoryList = new TreeMap<>(){{
        put("apple", "Apple");
        put("lenovo", "Lenovo");
        put("asus", "Asus");
        put("acer", "Acer");
        put("hp", "HP");
        put("dell", "Dell");
        put("msi", "MSI");
    }};

    public static final Map<String, String> targetList = new LinkedHashMap<>(){{
        put("gaming-do-hoa", "Gaming - Đồ hoạ");
        put("sinh-vien-van-phong", "Sinh viên - Văn phòng");
        put("mong-nhe", "Mỏng nhẹ");
        put("doanh-nhan", "Doanh nhân");
        put("ai", "AI");
    }};

    public static final Map<String, String> statusList = new LinkedHashMap<>(){{
        put("new", "New");
        put("like-new", "Like new");
    }};

    public static final Map<String, String> screenSizeList = new LinkedHashMap<>(){{
        put("duoi-14-inch", "Dưới 14 inch");
        put("14-15-inch", "Từ 14 - 15 inch");
        put("15-17-inch", "Từ 15 - 17 inch");
    }};
}
