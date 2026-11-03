/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_1268
 *  net.minecraft.class_1764
 *  net.minecraft.class_4587
 */
package aethereal;

import aethereal.FloatSetting;
import aethereal.HandOffsetEvent;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.Setting;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1764;
import net.minecraft.class_4587;

public class ViewModel
extends Module {
    private final FloatSetting field0060 = (FloatSetting)((Setting)((Setting)new FloatSetting("viewmodel.mainhandx", 0.0f, -3.0f, 3.0f, 0.05f).method1007("Main Hand X")).method0210("Horizontal offset for main hand")).method2130("\u0413\u043e\u0440\u0438\u0437\u043e\u043d\u0442\u0430\u043b\u044c\u043d\u043e\u0435 \u0441\u043c\u0435\u0449\u0435\u043d\u0438\u0435 \u043f\u0440\u0430\u0432\u043e\u0439 \u0440\u0443\u043a\u0438");
    private final FloatSetting field1450 = (FloatSetting)((Setting)((Setting)new FloatSetting("viewmodel.mainhandy", 0.0f, -2.0f, 2.0f, 0.05f).method1007("Main Hand Y")).method0210("Vertical offset for main hand")).method2130("\u0412\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0435 \u0441\u043c\u0435\u0449\u0435\u043d\u0438\u0435 \u043f\u0440\u0430\u0432\u043e\u0439 \u0440\u0443\u043a\u0438");
    private final FloatSetting field0985 = (FloatSetting)((Setting)((Setting)new FloatSetting("viewmodel.mainhandz", 0.0f, -3.0f, 3.0f, 0.05f).method1007("Main Hand Z")).method0210("Depth offset for main hand")).method2130("\u0421\u043c\u0435\u0449\u0435\u043d\u0438\u0435 \u0433\u043b\u0443\u0431\u0438\u043d\u044b \u043f\u0440\u0430\u0432\u043e\u0439 \u0440\u0443\u043a\u0438");
    private final FloatSetting field0190 = (FloatSetting)((Setting)((Setting)new FloatSetting("viewmodel.offhandx", 0.0f, -3.0f, 3.0f, 0.05f).method1007("Off Hand X")).method0210("Horizontal offset for off hand")).method2130("\u0413\u043e\u0440\u0438\u0437\u043e\u043d\u0442\u0430\u043b\u044c\u043d\u043e\u0435 \u0441\u043c\u0435\u0449\u0435\u043d\u0438\u0435 \u043b\u0435\u0432\u043e\u0439 \u0440\u0443\u043a\u0438");
    private final FloatSetting field0470 = (FloatSetting)((Setting)((Setting)new FloatSetting("viewmodel.offhandy", 0.0f, -2.0f, 2.0f, 0.05f).method1007("Off Hand Y")).method0210("Vertical offset for off hand")).method2130("\u0412\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0435 \u0441\u043c\u0435\u0449\u0435\u043d\u0438\u0435 \u043b\u0435\u0432\u043e\u0439 \u0440\u0443\u043a\u0438");
    private final FloatSetting field1627 = (FloatSetting)((Setting)((Setting)new FloatSetting("viewmodel.offhandz", 0.0f, -3.0f, 3.0f, 0.05f).method1007("Off Hand Z")).method0210("Depth offset for off hand")).method2130("\u0421\u043c\u0435\u0449\u0435\u043d\u0438\u0435 \u0433\u043b\u0443\u0431\u0438\u043d\u044b \u043b\u0435\u0432\u043e\u0439 \u0440\u0443\u043a\u0438");

    public ViewModel() {
        super("ViewModel", ModuleCategory.field1004, "Customizes held item rendering position and scale");
        this.method1013("\u041d\u0430\u0441\u0442\u0440\u0430\u0438\u0432\u0430\u0435\u0442 \u043f\u043e\u0437\u0438\u0446\u0438\u044e \u0438 \u0440\u0430\u0437\u043c\u0435\u0440 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430 \u0432 \u0440\u0443\u043a\u0435");
    }

    @EventHandler
    public void onHandOffset(HandOffsetEvent event) {
        float z;
        float y;
        float x;
        class_1268 hand = event.method1966();
        if (hand == class_1268.field_5808 && event.method1625().method_7909() instanceof class_1764) {
            return;
        }
        class_4587 matrix = event.method1808();
        if (hand == class_1268.field_5808) {
            x = ((Float)this.field0060.method0492()).floatValue();
            y = ((Float)this.field1450.method0492()).floatValue();
            z = ((Float)this.field0985.method0492()).floatValue();
        } else {
            x = ((Float)this.field0190.method0492()).floatValue();
            y = ((Float)this.field0470.method0492()).floatValue();
            z = ((Float)this.field1627.method0492()).floatValue();
        }
        matrix.method_46416(x, y, z);
    }
}

