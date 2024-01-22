package org.example;//********************************************************************
//  DeckOfCards.java       Author: Lewis and Loftus
//
//  Solution to Programming Project 7.7 
//********************************************************************

import java.util.Random;

public class DeckOfCards
{
   private Card[] cards;
   private int numCards;
   private int MAX_CARDS = 52, SWAPS = 999999;

   //-----------------------------------------------------------------
   //  Creates a full deck with the cards initially "in order".
   //-----------------------------------------------------------------
   public DeckOfCards()
   {
      numCards = MAX_CARDS;
      cards = new Card[numCards];

      int cardIndex = 0;
      for (int face = Card.ACE; face <= Card.KING; face++)
         for (int suit = Card.CLUBS; suit <= Card.SPADES; suit++)
            cards[cardIndex++] = new Card(face, suit);
   }

   //-----------------------------------------------------------------
   //  Deals a card from the deck.
   //-----------------------------------------------------------------
   public Card deal()
   {
      if (numCards > 0)
         return cards[--numCards];
      else
         return null;
   }

   //-----------------------------------------------------------------
   //  Returns the number of cards left in the deck.
   //-----------------------------------------------------------------
   public int getNumCardsInDeck()
   {
      return numCards;
   }

   //-----------------------------------------------------------------
   //  Returns true is the deck has cards in it, else false.
   //-----------------------------------------------------------------
   public boolean hasMoreCards()
   {
      return (numCards > 0);
   }

   //-----------------------------------------------------------------
   //  Shuffles the deck by swapping random pairs of cards many times.
   //-----------------------------------------------------------------
   public void shuffle()
   {
      Random gen = new Random();
      Card temp;
      int position1, position2;

      for (int i=1; i <= SWAPS; i++)
      {
         position1 = gen.nextInt(MAX_CARDS);
         position2 = gen.nextInt(MAX_CARDS);

         temp = cards[position1];
         cards[position1] = cards[position2];
         cards[position2] = temp;
      }
   }
}
