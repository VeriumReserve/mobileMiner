package android.vericoin.info.veriumminer.models;

import com.google.gson.annotations.SerializedName;

public class PoolItem {
    @SerializedName("id")
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @SerializedName("name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @SerializedName("poolAddress")
    private String poolAddress;
    public String getPoolAddress() { return poolAddress; }
    public void setPoolAddress(String poolAddress) { this.poolAddress = poolAddress; }

    @SerializedName("vipEndDate")
    private String vipEndDate;
    public String getVipEndDate() { return vipEndDate; }
    public void setVipEndDate(String vipEndDate) { this.vipEndDate = vipEndDate; }

    @SerializedName("lastStatistic")
    private LastStatistic lastStatistic;
    public LastStatistic getLastStatistic() { return lastStatistic; }
    public void setLastStatistic(LastStatistic lastStatistic) { this.lastStatistic = lastStatistic; }

    public PoolItem(int id, String name, String poolAddress, String vipEndDate)
    {
        this.id = id;
        this.name = name;
        this.poolAddress = poolAddress;
        this.vipEndDate = vipEndDate;
    }
}
