package com.inloadings.voidWorldGenerator.util;

import org.bukkit.generator.ChunkGenerator;

public class VoidWorldGenerator extends ChunkGenerator {

    @Override
    public boolean shouldGenerateNoise() {
        return false;
    }
}
