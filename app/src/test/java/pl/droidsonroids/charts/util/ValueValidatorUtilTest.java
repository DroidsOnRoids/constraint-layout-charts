package pl.droidsonroids.charts.util;

import java.util.Arrays;
import org.junit.Test;
import pl.droidsonroids.charts.model.Item;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by grzegorzmatyszczak on 19/07/16.
 */
public class ValueValidatorUtilTest {

    private Item mItemValid1 = new Item("", "52");
    private Item mItemValid2 = new Item("", "95");
    private Item mItemMin = new Item("", "0");
    private Item mItemMax = new Item("", "100");
    private Item mItemAboveMax = new Item("", "101");
    private Item mItemEmpty = new Item("", "");

    @Test
    public void isValid_singleValid() throws Exception {
        assertThat(ValueValidatorUtil.isValid(mItemValid1.getPercentString())).isEqualTo(true);
    }

    @Test
    public void isValid_singleBoundaryValues() throws Exception {
        assertThat(ValueValidatorUtil.isValid(mItemMax.getPercentString())).isEqualTo(true);
        assertThat(ValueValidatorUtil.isValid(mItemMin.getPercentString())).isEqualTo(true);
    }

    @Test
    public void isValid_singleInvalidValues() throws Exception {
        assertThat(ValueValidatorUtil.isValid(mItemAboveMax.getPercentString())).isEqualTo(false);
        assertThat(ValueValidatorUtil.isValid(mItemEmpty.getPercentString())).isEqualTo(false);
    }

    @Test
    public void isValid_listValid() throws Exception {
        assertThat(ValueValidatorUtil.isValid(Arrays.asList(mItemValid1, mItemValid2))).isEqualTo(true);
    }

    @Test
    public void isValid_listInvalid() throws Exception {
        assertThat(ValueValidatorUtil.isValid(Arrays.asList(mItemValid1, mItemAboveMax))).isEqualTo(false);
    }

}