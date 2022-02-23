package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String startGuess() {
        return "dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String postGuess(@PathVariable int guess, Model model) {
        //random number generator

        String message = "";
        int count = 0;

        //made a list to contain the number of dice rolls
        List<Integer> diceRolls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int diceRoll = (int) (Math.random() * (6)) + 1;
            diceRolls.add(diceRoll);
        }

        for (int dice : diceRolls) {
            if (dice == guess) {
                count++;
                message = "Your guess matched with " + count + " dice";
            }
        }


        //first part of exercise if the random number equals guess
        //then message will say user guessed correctly

//        if(diceRoll == guess){
//           message = "You guess correctly";
//        }else{
//            message = "You didn't guess correctly";
//        }
        model.addAttribute("message", message);
        model.addAttribute("guess", guess);
//        model.addAttribute("diceRoll", diceRoll);
        model.addAttribute("diceRolls", diceRolls);


        return "dice";
    }

    @PostMapping("/roll-dice")
    public String tryAgain() {
        return "dice";
    }
}
