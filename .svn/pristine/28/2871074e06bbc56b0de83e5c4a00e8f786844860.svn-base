package fengkongweishi.entity.personreport.vo;

import com.alibaba.fastjson.JSONObject;
import fengkongweishi.entity.personreport.po.CarrierPO;
import fengkongweishi.entity.personreport.po.IAnalyseItem;
import fengkongweishi.enums.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通话名单
 *
 * @author huanghengkun
 * @date 2018/01/31
 */

public class CallListVO implements IAnalyseItem {
    /**
     * 通话详单
     */
    private List callList;

    private Color color;

    @Override
    public String toString() {
        return "CallListVO{" +
                "callList=" + callList +
                '}';
    }

    public CallListVO(CarrierPO carrierPO) {
        if (carrierPO != null) {
            this.color = carrierPO.getColor();
            if (carrierPO.getCallList() != null) {
                this.callList = carrierPO.getCallList().stream()
                        .sorted(CallListVO::sorted)
                        .collect(Collectors.toList());
            } else {
                this.callList = Collections.emptyList();
            }
        }
    }

    private static int sorted(Object row1, Object row2) {
        return count((JSONObject) row2) - count((JSONObject) row1);
    }

    private static int count(JSONObject callListItem) {
        return callListItem.getInteger("dialedCnt") + callListItem.getInteger("dialCnt");
    }

    public List getCallList() {
        return callList;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
