package Instacart;

import java.io.*;
import java.util.*;

/*
 * You will be building a program to play a card game. 
 * The objective of the game is to form a hand of 3 cards that for each 3 different properties are either same or all different
 * 
 * The properties of a card are:
 * 1. Prefix: +, - or =
 * 2. Letter: A, B or C
 * 3. Number of letters: 1, 2 or 3 (eg A, AA or AAA)
 * 
 * For example, given the following set of cards
 * -A, -B, -BB, +C, -C, -CC, =CCC
 * 
 * There are 2 possible hands
 * +C -CC =CCC
 * 1. Prefix: +, -, = | All different
 * 2. Letter: C | All same
 * 3. Number of letters: 1, 2, 3 | All different
 * 
 * -A -B -C
 * 1. Prefix:  - | All same
 * 2. Letter: A, B, C | All different
 * 3. Number of letters: 1 | All same
 */
class Card implements Comparable<Card>{

    public static char[] prefixMap = {'+', '-', '='};
    public static char[] letterMap = {'A', 'B', 'C'}; 
    int _prefixIdx;
    int _letterIdx;
    int _numberIdx;
    String _cardStr;
    
    public Card(String card) {
        switch (card.charAt(0)) {
        case '+':
            _prefixIdx = 0; 
            break;
        case '-':
            _prefixIdx = 1;
            break;
        case '=':
        default:
            _prefixIdx = 2;            
        }

        _letterIdx = card.charAt(1) - 'A';
        _numberIdx = card.length() - 2;
        _cardStr = card;
    }
    
    public Card(int prefix, int letter, int number) {
        _prefixIdx = prefix;
        _letterIdx = letter;
        _numberIdx = number;
        _cardStr = null;
    }
    
    public Card(Card card1, Card card2) {
        _prefixIdx = getThirdInt(card1._prefixIdx, card2._prefixIdx);
        _letterIdx = getThirdInt(card1._letterIdx, card2._letterIdx);
        _numberIdx = getThirdInt(card1._numberIdx, card2._numberIdx);
    }
    
    private static int getThirdInt(int x, int y) {
        return x == y ? x : 3 - x - y;
    }
    
    public int getCardIdx() {
        return _prefixIdx + _letterIdx * 3 + _numberIdx * 9;
    }

    public boolean equals(Card c) {
        return this.getCardIdx() == c.getCardIdx();
    }
    
    @Override
    public int compareTo(Card c) {
        return this.getCardIdx() - c.getCardIdx();
    }
    @Override
    public String toString() {
        if (_cardStr == null) {
            StringBuffer buf = new StringBuffer();
            buf.append(prefixMap[_prefixIdx]);
            char c = letterMap[_letterIdx];
            for (int i = 0; i <= _numberIdx; i++) {
                buf.append(c);
            }
            _cardStr = buf.toString();
        }
        return _cardStr;
    }

}
public class ThreeCards {
    private static String getCombineThreeCard(Card c1, Card c2, Card c3) {
        Card cards[] = new Card[3];
        cards[0] = c1;
        cards[1] = c2;
        cards[2] = c3;
        
        Arrays.sort(cards);
        return cards[0].toString()+" "+cards[1].toString()+" "+cards[2].toString();
    }
    
    private static List<String> findCards(String cardsStr[]) {
        Set<String> set = new HashSet<>();
        int count[] = new int[27];
        Card cards[] = new Card[cardsStr.length];
        for (int i = 0; i < cardsStr.length; i++) {
            cards[i] = new Card(cardsStr[i].trim());
            count[cards[i].getCardIdx()]++;
        }
        
        for (int i = 0; i < cards.length; i++) {
            Card c1 = cards[i];
            for (int prefix = 0; prefix < 3; prefix++)
                for (int letter = 0; letter < 3; letter++)
                    for (int number = 0; number < 3; number++) {
                        Card c2 = new Card(prefix, letter, number);
                        Card c3 = new Card(c1 , c2);
                        if (!c1.equals(c2) && count[c2.getCardIdx()] > 0 && count[c3.getCardIdx()] > 0) {
                            set.add(getCombineThreeCard(c1, c2, c3));
                        } else if (c1.equals(c2) && count[c2.getCardIdx()] > 1 && count[c3.getCardIdx()] > 0){
                            set.add(getCombineThreeCard(c1, c2, c3));
                        }
                        
                    }
        }
        
        return new ArrayList<String>(set);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        
        List<String> output = findCards(text.split(","));
        
        for (String s:output) {
            System.out.println(s);
        }
        
    }

}
