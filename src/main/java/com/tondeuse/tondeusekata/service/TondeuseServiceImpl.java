package com.tondeuse.tondeusekata.service;

import com.tondeuse.tondeusekata.domaine.Tondeuse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TondeuseServiceImpl implements TondeuseService{

    List<List<Tondeuse>> pelouse;

    private List<Tondeuse> tondeuses  = new ArrayList<>();
    @Override
    public void initializePelouse(int maxX, int maxY) {
        pelouse = IntStream.range(0, maxX)
                .mapToObj(i -> IntStream.range(0, maxY)
                        .mapToObj(j -> (Tondeuse) null)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Override
    public void addTondeuse(Tondeuse tondeuse) {
        tondeuses.add(tondeuse);
        pelouse.get(tondeuse.getX()).set(tondeuse.getY(),tondeuse);
    }

    @Override
    public Tondeuse move(Tondeuse tondeuse) {
        String ins = tondeuse.getInstruction();
        for (char instruction : ins.toCharArray()) {
            switch (instruction) {
                case 'D' -> tondeuse.setOrientation(tondeuse.getOrientation().rotateRight());
                case 'G' -> tondeuse.setOrientation(tondeuse.getOrientation().rotateLeft());
                case 'A' -> tondeuse.getOrientation().moveForward(tondeuse);
            }
        }
        tondeuse.setInstruction(null);
        return tondeuse;
    }

    @Override
    public List<Tondeuse> moveAll() {
        this.tondeuses.forEach(this::move);
        return tondeuses;
    }
}
