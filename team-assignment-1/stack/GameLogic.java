package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class GameLogic {
    private final Stack<Card> deck = new Stack<>();
    private final ArrayList<Card> playerHand = new ArrayList<>();
    private final Stack<Card> discardPile = new Stack<>();
    private final Stack<Action> undoStack = new Stack<>();
    private final Stack<Action> redoStack = new Stack<>();

    public GameLogic() {
        setupGame();
    }

    private void setupGame() {
        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        ArrayList<Card> cardList = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                cardList.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cardList);
        for (Card card : cardList) {
            deck.push(card);
        }
    }

    public String drawAction() {
        if (deck.isEmpty()) {
            return "The deck is empty!";
        }
        Card drawnCard = deck.pop();
        playerHand.add(drawnCard);
        undoStack.push(new Action("draw", drawnCard));
        redoStack.clear();
        return "You drew the card: " + drawnCard;
    }

    public String discardAction() {
        if (playerHand.isEmpty()) {
            return "Your hand is empty, no card to discard.";
        }
        Card discardedCard = playerHand.removeLast();
        discardPile.push(discardedCard);
        undoStack.push(new Action("discard", discardedCard));
        redoStack.clear();
        return "You discarded the card: " + discardedCard;
    }

    public String undoAction() {
        if (undoStack.isEmpty()) {
            return "Nothing to undo.";
        }
        Action lastAction = undoStack.pop();
        if (lastAction.actionType.equals("draw")) {
            playerHand.remove(lastAction.card);
            deck.push(lastAction.card);
        } else if (lastAction.actionType.equals("discard")) {
            discardPile.pop();
            playerHand.add(lastAction.card);
        }
        redoStack.push(lastAction);
        return "UNDO: Reverting action '" + lastAction.actionType + "'";
    }

    public String redoAction() {
        if (redoStack.isEmpty()) {
            return "Nothing to redo.";
        }
        Action actionToRedo = redoStack.pop();
        if (actionToRedo.actionType.equals("draw")) {
            deck.pop();
            playerHand.add(actionToRedo.card);
        } else if (actionToRedo.actionType.equals("discard")) {
            playerHand.remove(actionToRedo.card);
            discardPile.push(actionToRedo.card);
        }
        undoStack.push(actionToRedo);
        return "REDO: Re-applying action '" + actionToRedo.actionType + "'";
    }

    public String getGameStatus() {
        StringBuilder status = new StringBuilder();
        status.append("Cards in Hand: ").append(playerHand).append("\n");
        status.append("Cards left in Deck: ").append(deck.size()).append("\n");
        if (!discardPile.isEmpty()) {
            status.append("Top of Discard Pile: ").append(discardPile.peek());
        } else {
            status.append("Discard Pile: [Empty]");
        }
        return status.toString();
    }
}