package fa.nfa;

import fa.State;
import fa.dfa.DFA;

import java.util.Set;

public class NFA implements NFAInterface{
    /**
     * @return equivalent DFA
     */
    @Override
    public DFA getDFA() {
        return null;
    }

    /**
     * Return delta entries
     *
     * @param from   - the source state
     * @param onSymb - the label of the transition
     * @return a set of sink states
     */
    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.getTo(onSymb);
    }

    /**
     * Traverses all epsilon transitions and determine
     * what states can be reached from s through e
     *
     * @param s
     * @return set of states that can be reached from s on epsilon trans.
     */
    @Override
    public Set<NFAState> eClosure(NFAState s)
    {
        NFA nfa = new NFA();
//        what you can reach with "e"
//        set of states
        return null;
    }

    /**
     * Adds the initial state to the DFA instance
     *
     * @param name is the label of the start state
     */
    @Override
    public void addStartState(String name) {

    }

    /**
     * Adds a non-final, not initial state to the DFA instance
     *
     * @param name is the label of the state
     */
    @Override
    public void addState(String name) {

    }

    /**
     * Adds a final state to the DFA
     *
     * @param name is the label of the state
     */
    @Override
    public void addFinalState(String name) {

    }

    /**
     * Adds the transition to the DFA's delta data structure
     *
     * @param fromState is the label of the state where the transition starts
     * @param onSymb    is the symbol from the DFA's alphabet.
     * @param toState   is the label of the state where the transition ends
     */
    @Override
    public void addTransition(String fromState, char onSymb, String toState) {

    }

    /**
     * Getter for Q
     *
     * @return a set of states that FA has
     */
    @Override
    public Set<? extends State> getStates()
    {
        return null;
    }

    /**
     * Getter for F
     *
     * @return a set of final states that FA has
     */
    @Override
    public Set<? extends State> getFinalStates()
    {
        return null;
    }

    /**
     * Getter for q0
     *
     * @return the start state of FA
     */
    @Override
    public State getStartState()
    {
        return null;
    }

    /**
     * Getter for the alphabet Sigma
     *
     * @return the alphabet of FA
     */
    @Override
    public Set<Character> getABC()
    {
        return null;
    }
}
