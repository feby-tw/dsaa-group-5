package stack;

class Action {
    String actionType;
    Card card;

    public Action(String actionType, Card card) {
        this.actionType = actionType;
        this.card = card;
    }

    @Override
    public String toString() {
        return actionType + " -> " + card;
    }
}