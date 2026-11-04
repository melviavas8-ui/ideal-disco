package aethereal;

import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_429;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_500;
import net.minecraft.class_526;

/**
 * Main menu (layout from the forum "velocity." menu).
 * Uses ONLY vanilla drawing (fills + font providers): no custom shaders, so it works on every
 * renderer (MobileGlues, Vulkan/Zink, ANGLE, native GL...).
 * Fonts: Inter Bold / Inter ExtraBold via vanilla TTF font providers
 * (assets/daamky/font/menu_bold.json, menu_extrabold.json) -> smooth, not the pixel Minecraft font.
 */
public class DaamkyMainMenu extends class_437 {
   private static final String LOGO = "daamky.";
   private static final String[] LABELS = new String[]{"Одиночная игра", "Сетевая игра", "Настройки", "Прокси", "Выход"};

   private static final class_2583 STYLE_BOLD = class_2583.field_24360.method_27704(class_2960.method_60655("daamky", "menu_bold"));
   private static final class_2583 STYLE_EXTRABOLD = class_2583.field_24360.method_27704(class_2960.method_60655("daamky", "menu_extrabold"));

   // provider "size" is 10 -> cap height = 0.7275 * 10 px, baseline sits 7 px below the draw y
   private static final float CAP = 7.275F;
   private static final float BASE = 7.0F;

   private final float[] hov = new float[5];
   private float s = 1.0F;

   public DaamkyMainMenu() {
      super(class_2561.method_43470("Daamky"));
   }

   // renderBackground -> nothing (background is painted in render)
   public void method_25420(class_332 var1, int var2, int var3, float var4) {
   }

   public boolean method_25422() {
      return false;
   }

   // ---- layout (same numbers as the forum menu, multiplied by scale s) ----
   private float[][] layout() {
      float cx = this.field_22789 / 2.0F;
      float bw = 114.0F * this.s;
      float bh = 24.0F * this.s;
      float gap = 5.0F * this.s;
      float row1Y = this.field_22790 / 2.0F - 18.0F * this.s;
      float bh2 = 20.0F * this.s;
      float gap2 = 4.0F * this.s;
      float rowW = 2.0F * bw + gap;
      float bw2 = (rowW - 2.0F * gap2) / 3.0F;
      float row2X = cx - rowW / 2.0F;
      float row2Y = row1Y + bh + gap2;
      return new float[][]{
         {cx - bw - gap / 2.0F, row1Y, bw, bh},
         {cx + gap / 2.0F, row1Y, bw, bh},
         {row2X, row2Y, bw2, bh2},
         {row2X + bw2 + gap2, row2Y, bw2, bh2},
         {row2X + 2.0F * (bw2 + gap2), row2Y, bw2, bh2}
      };
   }

   public void method_25394(class_332 var1, int mouseX, int mouseY, float var4) {
      int w = this.field_22789;
      int h = this.field_22790;
      if (w <= 0 || h <= 0) {
         return;
      }

      // same fixed sizes as the forum menu (scale kept as a field in case you want to tweak it)
      this.s = 1.0F;

      this.drawBackground(var1, w, h);

      float[][] b = this.layout();
      this.drawLogo(var1, b[0][1]);
      for (int i = 0; i < 5; i++) {
         boolean over = mouseX >= b[i][0] && mouseX <= b[i][0] + b[i][2] && mouseY >= b[i][1] && mouseY <= b[i][1] + b[i][3];
         this.hov[i] = Math.max(0.0F, Math.min(1.0F, this.hov[i] + (over ? 0.12F : -0.12F)));
         this.pill(var1, i, b[i], LABELS[i], i == 0);
      }
   }

   // ---------------------------------------------------------------- text
   /** draws text with its cap-height centered on (cx, capCenterY); k = size multiplier of the 10px provider font */
   private void drawCentered(class_332 c, String str, class_2583 style, float cx, float capCenterY, float k, int color) {
      class_327 tr = this.field_22787.field_1772;
      class_2561 text = class_2561.method_43470(str).method_10862(style);
      float width = tr.method_27525(text) * k;
      class_4587 ms = c.method_51448();
      ms.method_22903();
      ms.method_46416(cx - width / 2.0F, capCenterY - (CAP / 2.0F) * k - (BASE - CAP) * k, 0.0F);
      ms.method_22905(k, k, 1.0F);
      c.method_51439(tr, text, 0, 0, color, false);
      ms.method_22909();
   }

   private void drawLogo(class_332 c, float row1Y) {
      float k = 4.4F * this.s;
      // baseline sits 22*s above the first button row
      float baseline = row1Y - 22.0F * this.s;
      float capCenter = baseline - (CAP / 2.0F) * k;
      this.drawCentered(c, LOGO, STYLE_EXTRABOLD, this.field_22789 / 2.0F, capCenter, k, 0xFFFFFFFF);
   }

   // ---------------------------------------------------------------- background
   private void drawBackground(class_332 c, int w, int h) {
      double time = System.currentTimeMillis() / 1000.0;
      float shift = (float)(0.5 + 0.5 * Math.sin(time * 0.6));
      int tr0 = (int)(16 + shift * 10);
      int tg0 = (int)(16 + shift * 10);
      int tb0 = (int)(24 + shift * 14);
      int br0 = 7;
      int bg0 = 7;
      int bb0 = 11;
      for (int y = 0; y < h; y += 2) {
         float t = (float)y / (float)h;
         int r = (int)(tr0 + (br0 - tr0) * t);
         int g = (int)(tg0 + (bg0 - tg0) * t);
         int bl = (int)(tb0 + (bb0 - tb0) * t);
         c.method_25294(0, y, w, Math.min(y + 2, h), 0xFF000000 | (r << 16) | (g << 8) | bl);
      }
   }

   // ---------------------------------------------------------------- pills
   private void pill(class_332 c, int idx, float[] r, String label, boolean active) {
      float a = this.hov[idx];
      int ix = Math.round(r[0]);
      int iy = Math.round(r[1]);
      int iw = Math.round(r[2]);
      int ih = Math.round(r[3]);
      int rad = Math.max(2, Math.round(5.0F * this.s));
      int textColor;
      if (active) {
         int v = (int)(242 - a * 14);
         roundRect(c, ix, iy, iw, ih, rad, rgb(v, v, v + 4));
         textColor = rgb(18, 18, 24);
      } else {
         roundRect(c, ix, iy, iw, ih, rad, rgb(lerp(52, 112, a), lerp(52, 112, a), lerp(60, 124, a)));
         roundRectGradient(c, ix + 1, iy + 1, iw - 2, ih - 2, rad - 1,
            rgb(lerp(40, 54, a), lerp(40, 54, a), lerp(48, 64, a)), rgb(lerp(24, 36, a), lerp(24, 36, a), lerp(31, 44, a)));
         textColor = a > 0.5F ? rgb(240, 240, 245) : rgb(165, 165, 177);
      }

      float k = 0.85F * this.s;
      this.drawCentered(c, label, STYLE_BOLD, r[0] + r[2] / 2.0F, r[1] + r[3] / 2.0F, k, textColor);
   }

   private static int lerp(int from, int to, float t) {
      return (int)(from + (to - from) * t);
   }

   private static int rgb(int r, int g, int b) {
      return 0xFF000000 | (Math.max(0, Math.min(255, r)) << 16) | (Math.max(0, Math.min(255, g)) << 8) | Math.max(0, Math.min(255, b));
   }

   private static void roundRect(class_332 c, int x, int y, int w, int h, int r, int color) {
      if (w <= 0 || h <= 0) {
         return;
      }
      r = Math.max(1, Math.min(r, Math.min(w, h) / 2));
      for (int i = 0; i < r; i++) {
         double dy = r - i - 0.5;
         int inset = r - (int)Math.round(Math.sqrt(Math.max(0.0, (double)r * r - dy * dy)));
         c.method_25294(x + inset, y + i, x + w - inset, y + i + 1, color);
         c.method_25294(x + inset, y + h - i - 1, x + w - inset, y + h - i, color);
      }
      c.method_25294(x, y + r, x + w, y + h - r, color);
   }

   /** rounded rectangle with a vertical gradient (top -> bottom), per-row fills */
   private static void roundRectGradient(class_332 c, int x, int y, int w, int h, int r, int top, int bottom) {
      if (w <= 0 || h <= 0) {
         return;
      }
      r = Math.max(1, Math.min(r, Math.min(w, h) / 2));
      for (int i = 0; i < h; i++) {
         int inset = 0;
         if (i < r) {
            double dy = r - i - 0.5;
            inset = r - (int)Math.round(Math.sqrt(Math.max(0.0, (double)r * r - dy * dy)));
         } else if (i >= h - r) {
            double dy = r - (h - i - 1) - 0.5;
            inset = r - (int)Math.round(Math.sqrt(Math.max(0.0, (double)r * r - dy * dy)));
         }
         float t = h <= 1 ? 0.0F : (float)i / (float)(h - 1);
         int col = rgb(lerp((top >> 16) & 255, (bottom >> 16) & 255, t), lerp((top >> 8) & 255, (bottom >> 8) & 255, t), lerp(top & 255, bottom & 255, t));
         c.method_25294(x + inset, y + i, x + w - inset, y + i + 1, col);
      }
   }

   // ---------------------------------------------------------------- input
   public boolean method_25402(double mx, double my, int button) {
      if (button == 0) {
         float[][] b = this.layout();
         for (int i = 0; i < 5; i++) {
            if (mx >= b[i][0] && mx <= b[i][0] + b[i][2] && my >= b[i][1] && my <= b[i][1] + b[i][3]) {
               this.activate(i);
               return true;
            }
         }
      }

      return super.method_25402(mx, my, button);
   }

   private void activate(int idx) {
      switch (idx) {
         case 0:
            this.field_22787.method_1507(new class_526(this));
            break;
         case 1:
            this.field_22787.method_1507(new class_500(this));
            break;
         case 2:
            this.field_22787.method_1507(new class_429(this, this.field_22787.field_1690));
            break;
         case 3:
            this.field_22787.method_1507(new ProxyScreen(this));
            break;
         default:
            this.field_22787.method_1592();
      }
   }
}
