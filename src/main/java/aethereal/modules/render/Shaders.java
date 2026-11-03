/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  lombok.Generated
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 */
package aethereal;

import aethereal.DaamkyClient;
import aethereal.BooleanSetting;
import aethereal.ColorSetting;
import aethereal.DisplayNamed;
import aethereal.EntityFilter;
import aethereal.EnumSetting;
import aethereal.FloatSetting;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.MultiSelectSetting;
import aethereal.OutlineShaderRenderer;
import aethereal.RenderEntityEvent;
import aethereal.RenderHandEvent;
import aethereal.RenderShaderEvent;
import aethereal.Setting;
import java.awt.Color;
import java.util.Arrays;
import lombok.Generated;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1657;

public class Shaders
extends Module {
    private static Shaders field0268;
    private final OutlineShaderRenderer field0230 = new OutlineShaderRenderer();
    public EnumSetting<Mode> field0058 = (EnumSetting)((Setting)((Setting)new EnumSetting<Mode>("Mode", Mode.field1485).method1007("Mode")).method0210("Shader render mode")).method2130("\u0422\u0438\u043f \u043e\u0442\u0440\u0438\u0441\u043e\u0432\u043a\u0438");
    public ColorSetting field1443 = (ColorSetting)((Setting)((Setting)new ColorSetting("Color", 255, 152, 236, 75).method1882().method1007("Color")).method0210("Base shader color")).method2130("\u0426\u0432\u0435\u0442");
    public MultiSelectSetting field1005;
    public MultiSelectSetting field0202;
    public ColorSetting field0466;
    public ColorSetting field1623;
    public ColorSetting field1550;
    public ColorSetting field1711;
    public FloatSetting field1144;
    public FloatSetting field1097;
    public FloatSetting field1206;
    public ColorSetting field0878;
    public FloatSetting field0836;
    public FloatSetting field0919;
    public FloatSetting field1341;
    public ColorSetting field1299;
    public FloatSetting field1377;
    public FloatSetting field0393;
    public ColorSetting field0357;
    public BooleanSetting field0426;
    private boolean field0302;
    private boolean field0538;
    private boolean field0514;
    private boolean field0558;
    private boolean field1683;
    private boolean field1665;
    private boolean field1699;
    private boolean field1597;
    private boolean field1586;

    public OutlineShaderRenderer method1708() {
        return this.field0230;
    }

    public Shaders() {
        super("Shaders", ModuleCategory.field1004, "Applies shader effects to entities");
        String[] stringArray = new String[9];
        stringArray[0] = "Players";
        stringArray[1] = "Hostiles";
        stringArray[2] = "Animals";
        stringArray[3] = "Ambient";
        stringArray[4] = "Invisibles";
        stringArray[5] = "Items";
        stringArray[6] = "Crystals";
        stringArray[7] = "Others";
        stringArray[8] = "Hands";
        this.field1005 = (MultiSelectSetting)((Setting)((Setting)new MultiSelectSetting("Target", Arrays.asList(stringArray), false, () -> true).method1007("Target")).method0210("Entity types to apply shaders to")).method2130("\u0422\u0438\u043f\u044b \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439");
        this.field0202 = (MultiSelectSetting)((Setting)((Setting)new MultiSelectSetting("Enhance", Arrays.asList("Shine", "Glint", "Noise", "Circuit"), () -> this.field0058.method0492() != Mode.field0110).method1007("Enhance")).method0210("Shader enhancement effects")).method2130("\u042d\u0444\u0444\u0435\u043a\u0442\u044b \u0448\u0435\u0439\u0434\u0435\u0440\u0430");
        this.field0466 = (ColorSetting)((Setting)((Setting)new ColorSetting("HostilesColor", 250, 150, 255, 80, () -> (Boolean)this.field1005.method0439("Hostiles").method0492()).method1882().method1007("Hostiles Color")).method0210("Shader color for hostile mobs")).method2130("\u0426\u0432\u0435\u0442 \u0434\u043b\u044f \u0432\u0440\u0430\u0436\u0434\u0435\u0431\u043d\u044b\u0445 \u043c\u043e\u0431\u043e\u0432");
        this.field1623 = (ColorSetting)((Setting)((Setting)new ColorSetting("AnimalsColor", 100, 255, 100, 80, () -> (Boolean)this.field1005.method0439("Animals").method0492()).method1882().method1007("Animals Color")).method0210("Shader color for animals")).method2130("\u0426\u0432\u0435\u0442 \u0434\u043b\u044f \u0436\u0438\u0432\u043e\u0442\u043d\u044b\u0445");
        this.field1550 = (ColorSetting)((Setting)((Setting)new ColorSetting("AmbientColor", 150, 150, 255, 80, () -> (Boolean)this.field1005.method0439("Ambient").method0492()).method1882().method1007("Ambient Color")).method0210("Shader color for ambient creatures")).method2130("\u0426\u0432\u0435\u0442 \u0434\u043b\u044f \u0444\u043e\u043d\u043e\u0432\u044b\u0445 \u0441\u0443\u0449\u0435\u0441\u0442\u0432");
        this.field1711 = (ColorSetting)((Setting)((Setting)new ColorSetting("InvisiblesColor", 253, 121, 218, 80, () -> (Boolean)this.field1005.method0439("Invisibles").method0492()).method1882().method1007("Invisibles Color")).method0210("Shader color for invisible entities")).method2130("\u0426\u0432\u0435\u0442 \u0434\u043b\u044f \u043d\u0435\u0432\u0438\u0434\u0438\u043c\u044b\u0445 \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u0435\u0439");
        this.field1144 = (FloatSetting)((Setting)((Setting)new FloatSetting("GlintSpeed", 1.0f, 0.1f, 2.0f, 0.05f, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Glint").method0492() != false).method1007("Glint Speed")).method0210("Speed of the glint animation")).method2130("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438");
        this.field1097 = (FloatSetting)((Setting)((Setting)new FloatSetting("GlintWidth", 2.0f, 0.1f, 2.0f, 0.05f, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Glint").method0492() != false).method1007("Glint Width")).method0210("Width of the glint band")).method2130("\u0428\u0438\u0440\u0438\u043d\u0430 \u043f\u043e\u043b\u043e\u0441\u044b");
        this.field1206 = (FloatSetting)((Setting)((Setting)new FloatSetting("GlintGrad", 3.0f, 1.0f, 10.0f, 0.5f, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Glint").method0492() != false).method1007("Glint Gradient")).method0210("Glint gradient softness")).method2130("\u041c\u044f\u0433\u043a\u043e\u0441\u0442\u044c \u0433\u0440\u0430\u0434\u0438\u0435\u043d\u0442\u0430");
        this.field0878 = (ColorSetting)((Setting)((Setting)new ColorSetting("GlintColor", 255, 0, 200, 175, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Glint").method0492() != false).method1882().method1007("Glint Color")).method0210("Color of the glint effect")).method2130("\u0426\u0432\u0435\u0442 \u0431\u043b\u0438\u043a\u0430");
        this.field0836 = (FloatSetting)((Setting)((Setting)new FloatSetting("NoiseSpeed", 5.0f, 0.1f, 5.0f, 0.1f, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Noise").method0492() != false).method1007("Noise Speed")).method0210("Speed of noise animation")).method2130("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438");
        this.field0919 = (FloatSetting)((Setting)((Setting)new FloatSetting("NoiseAngle", 360.0f, 0.0f, 360.0f, 1.0f, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Noise").method0492() != false).method1007("Noise Angle")).method0210("Angle of noise pattern")).method2130("\u0423\u0433\u043e\u043b \u043f\u0430\u0442\u0442\u0435\u0440\u043d\u0430");
        this.field1341 = (FloatSetting)((Setting)((Setting)new FloatSetting("NoiseScale", 20.0f, 1.0f, 20.0f, 0.5f, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Noise").method0492() != false).method1007("Noise Scale")).method0210("Scale of noise pattern")).method2130("\u041c\u0430\u0441\u0448\u0442\u0430\u0431 \u043f\u0430\u0442\u0442\u0435\u0440\u043d\u0430");
        this.field1299 = (ColorSetting)((Setting)((Setting)new ColorSetting("NoiseColor", 255, 110, 221, 255, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Noise").method0492() != false).method1882().method1007("Noise Color")).method0210("Color of the noise effect")).method2130("\u0426\u0432\u0435\u0442 \u0448\u0443\u043c\u0430");
        this.field1377 = (FloatSetting)((Setting)((Setting)new FloatSetting("CircuitSpeed", 0.2f, 0.1f, 2.0f, 0.1f, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Circuit").method0492() != false).method1007("Circuit Speed")).method0210("Speed of circuit animation")).method2130("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438");
        this.field0393 = (FloatSetting)((Setting)((Setting)new FloatSetting("CircuitIntensity", 2.4f, 0.1f, 3.0f, 0.1f, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Circuit").method0492() != false).method1007("Circuit Intensity")).method0210("Intensity of circuit pattern")).method2130("\u0418\u043d\u0442\u0435\u043d\u0441\u0438\u0432\u043d\u043e\u0441\u0442\u044c \u043f\u0430\u0442\u0442\u0435\u0440\u043d\u0430");
        this.field0357 = (ColorSetting)((Setting)((Setting)new ColorSetting("CircuitColor", 212, 35, 129, 255, () -> this.field0058.method0492() != Mode.field0110 && (Boolean)this.field0202.method0439("Circuit").method0492() != false).method1882().method1007("Circuit Color")).method0210("Color of the circuit effect")).method2130("\u0426\u0432\u0435\u0442 \u044d\u0444\u0444\u0435\u043a\u0442\u0430");
        this.field0426 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("FriendColor", true).method1007("Friend Color")).method0210("Render friends in green instead of the default color")).method2130("\u0420\u0438\u0441\u043e\u0432\u0430\u0442\u044c \u0434\u0440\u0443\u0437\u0435\u0439 \u0437\u0435\u043b\u0451\u043d\u044b\u043c \u0446\u0432\u0435\u0442\u043e\u043c");
        this.method1013("\u041f\u0440\u0438\u043c\u0435\u043d\u044f\u0435\u0442 \u0448\u0435\u0439\u0434\u0435\u0440 \u043a \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u044f\u043c");
        field0268 = this;
        this.field1005.method0439("Players").method0206(true);
        this.field1005.method0439("Hostiles").method0206(true);
        this.field1005.method0439("Animals").method0206(true);
        this.field1005.method0439("Ambient").method0206(true);
        this.field1005.method0439("Invisibles").method0206(true);
        this.field1005.method0439("Items").method0206(true);
        this.field1005.method0439("Crystals").method0206(true);
        this.field1005.method0439("Others").method0206(true);
        this.field1005.method0439("Hands").method0206(true);
        this.field0202.method0439("Shine").method0206(true);
        this.field0202.method0439("Glint").method0206(true);
        this.field0202.method0439("Noise").method0206(true);
        this.field0202.method0439("Circuit").method0206(true);
    }

    @EventHandler
    public void onRenderShader(RenderShaderEvent event) {
        if (!this.method2195()) {
            return;
        }
        if (Shaders.field0796.field_1724 == null || Shaders.field0796.field_1687 == null) {
            return;
        }
        this.method0457();
        this.field0230.method0578();
    }

    @EventHandler
    public void onRenderEntity(RenderEntityEvent event) {
        if (!this.method2195()) {
            return;
        }
        if (Shaders.field0796.field_1724 == null || Shaders.field0796.field_1687 == null) {
            return;
        }
        if (!this.method1129(event.method1798())) {
            return;
        }
        Color color = this.method0234(event.method1798());
        event.method1516(this.field0230.method1517(event.method1630(), color));
    }

    @EventHandler
    public void onRenderEntity$POST(RenderEntityEvent.Pre event) {
        if (!this.method2195()) {
            return;
        }
        if (Shaders.field0796.field_1724 == null || Shaders.field0796.field_1687 == null) {
            return;
        }
        if (this.method0472()) {
            this.field0230.method2058().method0578();
            if (!this.field1586) {
                this.method2043();
            }
        }
    }

    @EventHandler
    public void onRenderHand(RenderHandEvent event) {
        if (!this.method2195()) {
            return;
        }
        if (Shaders.field0796.field_1724 == null || Shaders.field0796.field_1687 == null) {
            return;
        }
        if (!this.field1586) {
            return;
        }
        Color handColor = this.field1443.method1726();
        event.method1516(this.field0230.method1517(event.method1809(), handColor));
    }

    @EventHandler
    public void onRenderHand$POST(RenderHandEvent.Pre event) {
        if (!this.method2195()) {
            return;
        }
        if (Shaders.field0796.field_1724 == null || Shaders.field0796.field_1687 == null) {
            return;
        }
        if (!this.field1586) {
            return;
        }
        this.field0230.method2058().method0578();
    }

    @EventHandler
    public void onRenderShader$POST(RenderShaderEvent.Pre event) {
        if (!this.method2195()) {
            return;
        }
        if (Shaders.field0796.field_1724 == null || Shaders.field0796.field_1687 == null) {
            return;
        }
        if (!this.field1586) {
            return;
        }
        this.method2043();
    }

    private void method2043() {
        this.field0230.method0764(this.field0058.method0492() == Mode.field0686 ? 0 : (this.field0058.method0492() == Mode.field0110 ? 1 : 2), this.field1443.method1726(), (Boolean)this.field0202.method0439("Shine").method0492(), (Boolean)this.field0202.method0439("Glint").method0492(), ((Float)this.field1144.method0492()).floatValue(), ((Float)this.field1097.method0492()).floatValue(), ((Float)this.field1206.method0492()).floatValue(), this.field0878.method1726(), (Boolean)this.field0202.method0439("Noise").method0492(), ((Float)this.field0836.method0492()).floatValue(), ((Float)this.field0919.method0492()).floatValue(), ((Float)this.field1341.method0492()).floatValue(), this.field1299.method1726(), (Boolean)this.field0202.method0439("Circuit").method0492(), ((Float)this.field1377.method0492()).floatValue(), ((Float)this.field0393.method0492()).floatValue(), this.field0357.method1726());
    }

    public void method1691() {
        if (!this.method2195()) {
            return;
        }
        if (Shaders.field0796.field_1724 == null || Shaders.field0796.field_1687 == null) {
            return;
        }
        if (!this.field1586) {
            return;
        }
        this.field0230.method2058().method0578();
        this.method2043();
    }

    private boolean method0472() {
        return this.field0302 || this.field0538 || this.field0514 || this.field0558 || this.field1683 || this.field1665 || this.field1699 || this.field1597;
    }

    public boolean method1755() {
        return this.field1586;
    }

    public Color method2023() {
        return this.field1443.method1726();
    }

    private void method0457() {
        this.field0302 = (Boolean)this.field1005.method0439("Players").method0492();
        this.field0538 = (Boolean)this.field1005.method0439("Hostiles").method0492();
        this.field0514 = (Boolean)this.field1005.method0439("Animals").method0492();
        this.field0558 = (Boolean)this.field1005.method0439("Ambient").method0492();
        this.field1683 = (Boolean)this.field1005.method0439("Invisibles").method0492();
        this.field1665 = (Boolean)this.field1005.method0439("Items").method0492();
        this.field1699 = (Boolean)this.field1005.method0439("Crystals").method0492();
        this.field1597 = (Boolean)this.field1005.method0439("Others").method0492();
        this.field1586 = (Boolean)this.field1005.method0439("Hands").method0492();
    }

    public boolean method1129(class_1297 entity) {
        if (this.field0302 && EntityFilter.method1129(entity)) {
            return true;
        }
        if (this.field0538 && EntityFilter.method0235(entity)) {
            return true;
        }
        if (this.field0514 && EntityFilter.method2144(entity)) {
            return true;
        }
        if (this.field0558 && EntityFilter.method1851(entity)) {
            return true;
        }
        if (this.field1683 && entity.method_5767()) {
            return true;
        }
        if (this.field1665 && EntityFilter.method1660(entity)) {
            return true;
        }
        if (this.field1699 && EntityFilter.method1988(entity)) {
            return true;
        }
        return this.field1597;
    }

    private Color method0234(class_1297 entity) {
        if (entity instanceof class_1657) {
            boolean isFriend;
            class_1657 player = (class_1657)entity;
            boolean bl = isFriend = (Boolean)this.field0426.method0492() != false && DaamkyClient.method2004().method1608().method2135(player.method_5477().getString());
            if (isFriend) {
                Color base = this.field1443.method1726();
                return new Color(66, 245, 149, base.getAlpha());
            }
            return this.field1443.method1726();
        }
        EntityFilter.EntityKind category = EntityFilter.method2237(entity);
        return switch (category) {
            default -> throw new MatchException(null, null);
            case EntityFilter.EntityKind.field0644 -> this.field1443.method1726();
            case EntityFilter.EntityKind.field0074 -> this.field0466.method1726();
            case EntityFilter.EntityKind.field1459 -> this.field1623.method1726();
            case EntityFilter.EntityKind.field0994 -> this.field1550.method1726();
            case EntityFilter.EntityKind.field0773 -> this.field1711.method1726();
            case EntityFilter.EntityKind.field1257, EntityFilter.EntityKind.field0324, EntityFilter.EntityKind.field0196 -> this.field1443.method1726();
        };
    }

    @Generated
    public static Shaders method2009() {
        return field0268;
    }

    public static enum Mode implements DisplayNamed {
        field0686("Fill"),
        field0110("Outline"),
        field1485("Both");

        private final String field1030;

        private Mode(String name) {
            this.field1030 = name;
        }

        @Override
        public String method0557() {
            return this.field1030;
        }
    }
}