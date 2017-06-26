package sample.network;

import org.parceler.Parcel;
import java.util.ArrayList;


@Parcel
public class ApiResponse
{
    @SerializedName("gpslat")
    private String businessGPSLat;

    @SerializedName("gpslong")
    private String businessGPSLong;

    public String getGPSLat()
    {
        return GPSLat;
    }
    public String getGPSLong()
    {
        return GPSLong;
    }
}
