/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.fabricmc.api.ModInitializer
 */
package ua.patch.daamky.api;

import aethereal.DaamkyClient;
import net.fabricmc.api.ModInitializer;

public class Proxy
implements ModInitializer {
    DaamkyClient field0573 = new DaamkyClient();

    public void onInitialize() {
        this.field0573.method0578();
    }
}

