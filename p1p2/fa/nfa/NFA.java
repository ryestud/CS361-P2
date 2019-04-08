package fa.nfa;

import fa.State;
import fa.dfa.DFA;

import java.util.LinkedHashSet;
import java.util.Set;

public class NFA implements NFAInterface{
    private Set<NFAState> states;
    private NFAState start;
    private Set<Character> sigma;


    public NFA(){
        states = new LinkedHashSet<NFAState>();
        sigma = new LinkedHashSet<Character>();
    }


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
        NFAState s = checkIfExists(name);
    }

    @Override
    public void addState(String name) {

    }

    private NFAState checkIfExists(String name) {
        NFAState ret = null;
        for(NFAState s : states){
            if(s.getName().equals(name)){
                ret = s;
                break;
            }
        }
        return ret;
    }

    /**
     * Adds a non-final, not initial state to the NFA instance
     *
     * @param name is the label of the state
     */
    public void addState(NFAState name) {
        states.add(name);
    }

    /**
     * Adds a final state to the NFA
     *
     * @param name is the label of the state
     */
    @Override
    public void addFinalState(String name) {
        NFAState s = checkIfExists(name);
        if( s == null){
            s = new NFAState(name, true);
            addState(s);
        } else {
            System.out.println("WARNING: A state with name " + name + " already exists in the DFA");
        }
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
    public Set<NFAState> getStates()
    {
        return states;
    }

    /**
     * Getter for F
     * Set< ? extends State>
     * @return a set of final states that FA has
     */
    @Override
    public Set<NFAState> getFinalStates()
    {
        Set<NFAState> ret = new LinkedHashSet<NFAState>();
        for(NFAState s : states){
            if(s.isFinal()){
                ret.add(s);
            }
        }
        return ret;
    }

    /**
     * Getter for q0
     *
     * @return the start state of FA
     */
    @Override
    public State getStartState()
    {
        return start;
    }

    /**
     * Getter for the alphabet Sigma
     *
     * @return the alphabet of FA
     */
    @Override
    public Set<Character> getABC()
    {
        return sigma;
    }
}
