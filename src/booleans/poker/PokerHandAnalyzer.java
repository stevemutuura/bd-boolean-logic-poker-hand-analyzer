package booleans.poker;

public class PokerHandAnalyzer {

    public enum HandType {
        HIGH_CARD,
        PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH
    }


    private static HandType analyzeHand(Hand hand) {
        hand.sortCards();

        HandType answer = HandType.HIGH_CARD;
        int numPairs = hand.NUM_PAIRS();

        if (hand.IS_STRAIGHT()) {
            if (hand.NUM_SUITS() == 1) {
                if (hand.HIGHEST_RANK() == Card.Rank.ACE) {
                    answer = HandType.ROYAL_FLUSH;
                } else {
                    answer = HandType.STRAIGHT_FLUSH;
                }
            } else {
                answer = HandType.STRAIGHT;
            }
        } else if (hand.HAS_FOUR_OF_A_KIND()) {
            answer = HandType.FOUR_OF_A_KIND;
        } else if (numPairs == 2) {
            if (hand.HAS_THREE_OF_A_KIND()) {
                answer = HandType.FULL_HOUSE;
            } else {
                answer = HandType.TWO_PAIR;
            }
        } else if (hand.HAS_THREE_OF_A_KIND()) {
            answer = HandType.THREE_OF_A_KIND;
        } else if (hand.NUM_SUITS() == 1) {
            answer = HandType.FLUSH;
        } else if (numPairs == 1) {
            answer = HandType.PAIR;
        }
        return answer;
    }

    public static void main(String... args) {
        for (Hand hand : HandGenerator.generateHands()) {
            System.out.println();
            hand.printHand();
            System.out.println(analyzeHand(hand));
        }
    }
}
