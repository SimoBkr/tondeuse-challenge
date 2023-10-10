package com.tondeuse.tondeusekata;

import com.tondeuse.tondeusekata.domaine.Orientation;
import com.tondeuse.tondeusekata.domaine.Tondeuse;
import com.tondeuse.tondeusekata.service.TondeuseService;
import com.tondeuse.tondeusekata.service.TondeuseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@SpringBootApplication
@RequiredArgsConstructor
public class TondeuseKataApplication {

	public static void main(String[] args)   {
		TondeuseService tondeuseService = new TondeuseServiceImpl();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"));
			String line;
			int  i = 0;
			while ((line = reader.readLine()) != null) {
				if (i==0){
					String[] sizePel = line.split(" ");
					initPelouse(Integer.valueOf(sizePel[0]),Integer.valueOf(sizePel[1]),tondeuseService);
				}
				else{
					String[] sizeTend = line.split(" ");
					initTendeuse(Integer.valueOf(sizeTend[0]),
							Integer.valueOf(sizeTend[1]),
							Orientation.valueOf(sizeTend[2]),
							reader.readLine(),
							tondeuseService
					);
				}
				i++;
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Error reading input file: " + e.getMessage());
		}

		System.out.println("Final Positions:");
		for (Tondeuse tondeuse : tondeuseService.moveAll()) {
			System.out.println(tondeuse.getX() + " " + tondeuse.getY() + " " + tondeuse.getOrientation());
		}
	}

	private static void initTendeuse(Integer x, Integer y, Orientation o, String instruction, TondeuseService tondeuseService) {
		tondeuseService.addTondeuse(new Tondeuse(x,y,o,instruction));
	}

	private static void initPelouse(Integer x, Integer y, TondeuseService tondeuseService) {

		tondeuseService.initializePelouse(x, y);
	}

}


