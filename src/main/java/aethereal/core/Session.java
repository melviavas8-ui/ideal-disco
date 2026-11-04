package aethereal;

import net.minecraft.class_310;

public final class Session {
   private Session() {
   }

   public static String uid() {
      return "0";
   }

   public static String username() {
      try {
         class_310 mc = class_310.method_1551();
         if (mc != null && mc.method_1548() != null) {
            String name = mc.method_1548().method_1676();
            if (name != null && !name.isBlank()) {
               return name;
            }
         }
      } catch (Throwable ignored) {
      }
      return "Daamky";
   }

   public static String hwid() {
      return "local";
   }

   public static String token() {
      return "";
   }

   public static String role() {
      return "user";
   }

   public static String expire() {
      return "lifetime";
   }

   public static void hideWindow() {
   }

   public static String avatarUrl() {
      return "";
   }
}
