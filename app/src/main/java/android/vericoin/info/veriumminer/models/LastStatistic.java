package android.vericoin.info.veriumminer.models;


import com.google.gson.annotations.SerializedName;

public class LastStatistic {
    @SerializedName("date")
    private String date;
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @SerializedName("fee")
    private double fee;
    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    @SerializedName("hashRate")
    private double hashRate;
    public double getHashRate() { return hashRate; }
    public void setHashRate(double hashRate) { this.hashRate = hashRate; }

    @SerializedName("id")
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @SerializedName("poolId")
    private int poolId;
    public int getPoolId() { return poolId; }
    public void setPoolId(int poolId) { this.poolId = poolId; }

    @SerializedName("workers")
    private int workers;
    public int getWorkers() { return workers; }
    public void setWorkers(int workers) { this.workers = workers; }

    public LastStatistic(String date, int fee, int hashRate, int id, int poolId, int workers) {
        this.date = date;
        this.fee = fee;
        this.hashRate = hashRate;
        this.id = id;
        this.poolId = poolId;
        this.workers = workers;
    }
}
