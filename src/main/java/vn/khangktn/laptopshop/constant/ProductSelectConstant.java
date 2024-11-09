package vn.khangktn.laptopshop.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductSelectConstant {
    public static final Map<String, String> cpuList = new LinkedHashMap<>(){{
        put("intel-core-i9", "Intel Core i9");
        put("intel-core-i7", "Intel Core i7");
        put("intel-core-i5", "Intel Core i5");
        put("intel-core-i3", "Intel Core i4");
        put("apple-m1", "Apple M1");
        put("apple-m2", "Apple M2");
        put("apple-m3", "Apple M3");
    }};

    public static final Map<String, String> vgaList = new LinkedHashMap<>(){{
        put("nvidia-geforce-series", "NVIDIA GeForce");
        put("nvidia-geforce-mx-series", "NVIDIA GeForce MX");
        put("intel-gpu", "Intel GPU");
        put("amd-radeon", "AMD Radeon");
        put("apple-gpu", "Apple GPU");
    }};

    public static final Map<String, String> ramList = new LinkedHashMap<>(){{
        put("64", "64 GB");
        put("32", "32 GB");
        put("16", "16 GB");
        put("8", "8 GB");
    }};

    public static final Map<String, String> storageList = new LinkedHashMap<>(){{
        put("ssd-1-tb", "SSD 1 TB");
        put("ssd-512-gb", "SSD 512 GB");
        put("ssd-256-gb", "SSD 256 GB");
        put("ssd-128-gb", "SSD 128 GB");
    }};

    public static final Map<String, String> factoryList = new LinkedHashMap<>(){{
        put("apple", "Apple");
        put("lenovo", "Lenovo");
        put("asus", "Asus");
        put("acer", "Acer");
        put("hp", "HP");
        put("dell", "Dell");
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
}
