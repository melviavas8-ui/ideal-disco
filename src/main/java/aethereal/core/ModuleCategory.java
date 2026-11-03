package aethereal;

import lombok.Generated;

public enum ModuleCategory {
   field0661("v", "V", "Combat"),
   field0088("b", "K", "Movement"),
   field1470("o", "L", "Player"),
   field1004("p", "B", "Render"),
   field0776("n", "Z", "Misc");

   private final String field1269;
   private final String field0336;
   private final String displayName;

   @Generated
   ModuleCategory(String icon, String shortIcon, String displayName) {
      this.field1269 = icon;
      this.field0336 = shortIcon;
      this.displayName = displayName;
   }

   @Generated
   public String method0557() {
      return this.field1269;
   }

   @Generated
   public String method0017() {
      return this.field0336;
   }

   /** Human-readable category title for ClickGUI. */
   public String methodDisplayName() {
      return this.displayName;
   }
}
