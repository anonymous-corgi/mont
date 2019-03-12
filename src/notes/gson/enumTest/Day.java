package notes.gson.enumTest;

import com.google.gson.annotations.SerializedName;

public enum Day {
    MONDAY,
    @SerializedName("2")
    TUESDAY,
    @SerializedName("Three")
    WEDNESDAY
}
