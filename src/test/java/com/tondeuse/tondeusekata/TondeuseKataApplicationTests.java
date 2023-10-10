package com.tondeuse.tondeusekata;

import com.tondeuse.tondeusekata.domaine.Orientation;
import com.tondeuse.tondeusekata.domaine.Tondeuse;
import com.tondeuse.tondeusekata.service.TondeuseService;
import com.tondeuse.tondeusekata.service.TondeuseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class TondeuseKataApplicationTests {

    private TondeuseService tondeuseService;

    @BeforeEach
    void setUp() {
        tondeuseService = new TondeuseServiceImpl();
    }

    @Test
    void move_AllInstructionsFromFile_ExpectedFinalPositions() throws IOException {

        URL res = getClass().getClassLoader().getResource("input.txt");

        Path inputFile = Path.of(res.getPath());

        List<String> lines = Files.readAllLines(inputFile);

        String[] dimensions = lines.get(0).split(" ");
        int maxX = Integer.parseInt(dimensions[0]);
        int maxY = Integer.parseInt(dimensions[1]);

        tondeuseService.initializePelouse(maxX, maxY);

        for (int i = 1; i < lines.size(); i += 2) {
            String[] position = lines.get(i).split(" ");
            int x = Integer.parseInt(position[0]);
            int y = Integer.parseInt(position[1]);
            Orientation orientation = Orientation.valueOf(position[2]);

            String instruction = lines.get(i + 1);

            Tondeuse tondeuse = new Tondeuse(x, y, orientation, instruction);
            tondeuseService.addTondeuse(tondeuse);
        }

        List<Tondeuse> movedTondeuses = tondeuseService.moveAll();

        assertEquals(1, movedTondeuses.get(0).getX());
        assertEquals(3, movedTondeuses.get(0).getY());
        assertEquals(Orientation.N, movedTondeuses.get(0).getOrientation());
        assertNull(movedTondeuses.get(0).getInstruction());

        assertEquals(5, movedTondeuses.get(1).getX());
        assertEquals(1, movedTondeuses.get(1).getY());
        assertEquals(Orientation.E, movedTondeuses.get(1).getOrientation());
        assertNull(movedTondeuses.get(1).getInstruction());
    }
}
