package com.sepidehmiller.jokes;

import java.util.ArrayList;
import java.util.List;

public class DadJokes {

  private List<String> jokes;

  public DadJokes() {
    jokes = new ArrayList<String>();

    // Dad jokes stolen from buzzfeed.
    // https://www.buzzfeed.com/mikespohr/29-dad-jokes-that-are-so-bad-their-actually-good?utm_term=.hw7q2blwGg#.wvgVp2QAok

    jokes.add("What time did the man go to the dentist? Tooth hurt-y.");
    jokes.add("Did you hear about the guy who invented Lifesavers? They say he made a mint.");
    jokes.add("Whenever the cashier at the grocery store asks my dad if he would like the milk in a " +
        "bag he replies, 'No, just leave it in the carton!'");
    jokes.add("Why do chicken coops only have two doors? Because if they had four, they would be " +
        "chicken sedans!");
    jokes.add("Why did the Clydesdale give the pony a glass of water? Because he was a little " +
        "horse!");
    jokes.add("Two guys walk into a bar, the third one ducks.");
    jokes.add("I had a dream that I was a muffler last night. I woke up exhausted!");
    jokes.add("What's Forrest Gump's password? 1forrest1");


    // More dad jokes stolen from buzzfeed.
    // https://www.buzzfeed.com/mikespohr/75-dad-jokes-that-are-so-bad-theyre-actually-good?utm_term=.gg9EPAgRqa#.uy2Xpy2kKo
    jokes.add("I'm reading a book about anti-gravity. It's impossible to put down!");
    jokes.add("If you see a robbery at an Apple Store does that make you an iWitness?");
    jokes.add("What did the horse say after it tripped? \"Help! I’ve fallen and I can’t giddyup!\"");
    jokes.add("Why couldn't the bike standup by itself? It was two tired.");
    jokes.add("You know what the loudest pet you can get is? A trumpet.");
    jokes.add("What did the buffalo say to his son when he dropped him off at school? Bison.");


    //Jokes from https://www.reddit.com/r/dadjokes
    jokes.add("Why don't koalas count as bears? They don't have the koalafications.");
    jokes.add("What do you call a bear with no teeth? A gummy bear.");
    jokes.add("What kind of tea is hard to swallow? Reality.");
    jokes.add("Doctor: You broke your arm in three places. Me: I guess I’ll stay away from those places.");

    //https://www.tutorialspoint.com/java/util/collections_shuffle.htm
  }

  public String getJoke() {

    int index = (int) (Math.random() * jokes.size());
    return jokes.get(index);
  }
}

