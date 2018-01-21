package br.com.topin.topin.converters;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

public class StringListConverter {
    @TypeConverter
    public List<String> fromString(String value) {
        String[] list = value.split(",");
        return Arrays.asList(list);
    }

    @TypeConverter
    public String fromList(List<String> list) {
        return TextUtils.join(",", list);
    }
}
