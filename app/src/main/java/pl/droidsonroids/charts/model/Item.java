package pl.droidsonroids.charts.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.parceler.Parcel;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

@Data
@RequiredArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Parcel
public class Item {

    @NonNull String label;
    @NonNull String percentString;
    int percent;
}
