package com.zegames.graficos;

import com.zegames.main.Game;

import java.awt.*;

public class UI {
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("arail", Font.BOLD, 18));
        g.drawString("Maçãs:" + Game.frutas_contagem + "/" + Game.frutas_atual, 30, 25);
    }
}
