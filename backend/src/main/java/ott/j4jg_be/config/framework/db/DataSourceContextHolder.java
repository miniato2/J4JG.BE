package ott.j4jg_be.config.framework.db;


public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    public static void useMaster() {
        setDataSourceType("master");
    }

    public static void useSlave() {
        setDataSourceType("slave");
    }
}
