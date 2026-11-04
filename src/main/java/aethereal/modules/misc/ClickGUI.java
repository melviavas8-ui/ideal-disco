package aethereal;

import lombok.Generated;

public class ClickGUI extends Module {
   private static ClickGUI field0041;
   private final EnumSetting<ClickGUI.AnimationStyle> field1448 = new EnumSetting<>("clickgui.animationtype", ClickGUI.AnimationStyle.field0040)
      .method1007("Animation Type")
      .method0210("GUI open/close animation style")
      .method2130("Стиль анимации открытия/закрытия меню");
   private final EnumSetting<ClickGUI.DesignStyle> fieldDesign = new EnumSetting<>("clickgui.design", ClickGUI.DesignStyle.fieldNormal)
      .method1007("Design")
      .method0210("Panel visual style")
      .method2130("Внешний вид панелей");

   public ClickGUI() {
      super("ClickGUI", ModuleCategory.field0776, "Opens the module configuration menu");
      this.method1013("Открывает меню настройки модулей");
      field0041 = this;
      this.method2178(false);
   }

   public ClickGUI.AnimationStyle method1706() {
      return this.field1448.method0492();
   }

   public ClickGUI.DesignStyle methodDesign() {
      return this.fieldDesign.method0492();
   }

   public static boolean methodIsGlass() {
      ClickGUI gui = field0041;
      return gui != null && gui.methodDesign() == DesignStyle.fieldGlass;
   }

   @Generated
   public static ClickGUI method1683() {
      return field0041;
   }

   public enum AnimationStyle implements DisplayNamed {
      field0603("Default"),
      field0040("MacOS");

      private final String field1504;

      AnimationStyle(String var3) {
         this.field1504 = var3;
      }

      @Override
      public String method0557() {
         return this.field1504;
      }
   }

   public enum DesignStyle implements DisplayNamed {
      fieldNormal("Обычный"),
      fieldGlass("Стекло (beta)");

      private final String field1504;

      DesignStyle(String name) {
         this.field1504 = name;
      }

      @Override
      public String method0557() {
         return this.field1504;
      }
   }
}
