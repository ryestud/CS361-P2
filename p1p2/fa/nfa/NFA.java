package fa.nfa;

import fa.State;
import fa.dfa.DFA;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

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
        DFA dfa = new DFA();
        boolean isFinished = false;
        Set<NFAState> closureState = eClosure(start);
        LinkedList<HashSet<NFAState>> queue = new LinkedList<HashSet<NFAState>>();
        Set<Set<NFAState>> holder = new HashSet<>();
        HashSet<NFAState> newSet = new HashSet<NFAState>();
        newSet.add(start);
        newSet.addAll(eClosure(start));
        for (NFAState state : newSet) {
            if (state.isFinal()){
                isFinished = true;
            }
        } if (isFinished == true){
            dfa.addFinalState(newSet.toString());
        }
        dfa.addStartState(newSet.toString());
        queue.addFirst(newSet);
        while(!queue.isEmpty()){
            Set<NFAState> current = queue.removeLast();
            holder.add(current);
            String currentSet = current.toString();
            for (char symbol : sigma){
                isFinished = false;
                HashSet<NFAState> newStateSet = new HashSet<NFAState>();
                for (NFAState state : currState){
                    HashSet<NFAState> trans = state.getTo(symbol);
                    if (trans != null){
                        for (NFAState transition : trans){
                            this.eps = newSet;
                            eClosure(transition);
                            if (transition.isFinal()){
                                isFinished = true;
                            }
                            newSet.add(transition);
                        }
                    }
                }
                String name = newSet.toString();
                if (isFinished == true){
                    if (!holder.contains(newSet) && !queue.contains(newSet)){
                        dfa.addFinalState(name);
                    }
                    dfa.addTransition(currentSet, symbol, name);
                } else if (!temp.contains(newSet) && !queue.contains(newSet)){
                    dfa.addState(name);
                }
                dfa.addTransition(currentSet, symbol, name);
                if (!temp.contains(newSet) && !queue.contains(newSet)){
                    queue.addFirst(newSet);
                }
            }
        }
        return dfa;
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
        Set<NFAState> ret = from.getTo(onSymb);
        return ret;
    }

    /**
     * Traverses all epsilon transitions and determine
     * what states can be reached from s through e
     *
     * @param state
     * @return set of states that can be reached from s on epsilon trans.
     */
    @Override
    public Set<NFAState> eClosure(NFAState state)
    {
        Set<NFAState> nfa = new LinkedHashSet<>();
        Set<NFAState> ret = null;
        ret = DFS(state,nfa);
//        what you can reach with "e"
//        set of states
        return ret;
    }

    public Set<NFAState> DFS(NFAState state, Set<NFAState> closure){
        Set<NFAState> ret = new LinkedHashSet<>();
        Set<NFAState> visitedStates = closure;
        ret.add(state);
        return getDFS(visitedStates,state,ret);
    }

    private Set<NFAState> getDFS(Set<NFAState> visitedStates, NFAState state, Set<NFAState> ret) {
        if(!state.getTo('e').isEmpty() && !visitedStates.contains(state)){
            visitedStates.add(state);
            for(NFAState fromNFA : state.getTo('e')){
                ret.addAll(DFS(fromNFA, visitedStates));
            }
        }
        return ret;
    }

    /**
     * Adds the initial state to the DFA instance
     *
     * @param name is the label of the start state
     */
    @Override
    public void addStartState(String name) {
        NFAState state = checkIfExists(name);
        if(state != null){
            state = new NFAState(name);
            addState(state);
        }
        start = state;
    }

    @Override
    public void addState(String name) {
        if( name != null) {
            NFAState newState = new NFAState(name);
            addState(newState);
        }
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
        (checkIfExists(fromState)).addTransition(onSymb,checkIfExists(toState));
        if(sigma.contains(onSymb) && onSymb != 'e'){
            sigma.add(onSymb);
        }
    }

    private NFAState checkIfExists(String name) {
        NFAState ret = null;
        for(NFAState state : states){
            if(state.getName()==(name)){
                ret = state;
                return ret;
            }
        }
        return ret;
    }

//    public NFAState getState(String name){
//        NFAState ret = new NFAState(name);
//        for(NFAState state : states){
//            if(name.equals(state.getName())){
//                ret = state;
//                return ret;
//            }
//        }
//        return null;
//    }

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
