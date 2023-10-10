package com.tondeuse.tondeusekata.service;

import com.tondeuse.tondeusekata.domaine.Tondeuse;

import java.util.List;

public interface TondeuseService {
    void initializePelouse(int maxX, int maxY);
    void addTondeuse(Tondeuse tondeuse);
    Tondeuse move(Tondeuse tondeuse);
    List<Tondeuse> moveAll();
}
