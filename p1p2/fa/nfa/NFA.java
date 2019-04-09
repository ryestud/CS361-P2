package fa.nfa;

import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class NFA implements NFAInterface{
    private Set<NFAState> states;
    private NFAState start;
    private Set<Character> sigma;
    private Set<NFAState> closureSet;

    /**
     *
     */
    public NFA(){
        states = new LinkedHashSet<NFAState>();
        sigma = new LinkedHashSet<Character>();
        closureSet = new LinkedHashSet<NFAState>();
    }


    /**
     * @return equivalent DFA
     */
    @Override
    public DFA getDFA() {
        DFA dfa = new DFA();
        boolean isFinished = false;
        Set<NFAState> closureState = eClosure(this.start);
        LinkedList<HashSet<NFAState>> queue = new LinkedList<HashSet<NFAState>>();
        Set<Set<NFAState>> holder = new HashSet<Set<NFAState>>();
        HashSet<NFAState> nfaStates = new HashSet<NFAState>();
        nfaStates.add(this.start);
        nfaStates.addAll(closureState);
        for (NFAState state : nfaStates) {
            if (state.isFinal()){
                isFinished = true;
            }
        } if (isFinished == true){
            dfa.addFinalState(nfaStates.toString());
        }
        queue.add(nfaStates);
        dfa.addStartState(nfaStates.toString());
        while(!queue.isEmpty()){
            Set<NFAState> current = queue.remove();
            holder.add(current);
            String currentSet = current.toString();
            for (char symbol : this.sigma){
                isFinished = false;
                HashSet<NFAState> newStateSet = new HashSet<NFAState>();
                for (NFAState state : newStateSet){
                    HashSet<NFAState> trans = state.getTo(symbol);
                    if (trans != null){
                        for (NFAState transition : trans){
//                             this.closureSet = nfaStates;
                             this.closureSet = newStateSet;
                            eClosure(transition);
                            if (transition.isFinal()){
                                isFinished = true;
                            }
                            //building state name
                            nfaStates.add(transition);
                        }
                    }
                }
                String name = nfaStates.toString();
                if (isFinished == true){
                    if (!holder.contains(nfaStates) && !queue.contains(nfaStates)){
                        dfa.addFinalState(name);
                    }
                    dfa.addTransition(currentSet, symbol, name);
                } else if (!holder.contains(nfaStates) && !queue.contains(nfaStates)){
                    dfa.addState(name);
                }
                dfa.addTransition(currentSet, symbol, name);
                if (!holder.contains(nfaStates) && !queue.contains(nfaStates)){
                    queue.addFirst(nfaStates);
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
     * what states can be reached from state through e
     *
     * @param state
     * @return set of states that can be reached from state on epsilon trans.
     */
    @Override
    public Set<NFAState> eClosure(NFAState state)
    {
        Set<NFAState> temp = new LinkedHashSet<>();
        Set<NFAState> statesVisited = new LinkedHashSet<>();
//        if(!state.getTo('e').equals(new LinkedHashSet<NFAState>())){
        if(!state.getTo('e').isEmpty() && !statesVisited.contains(state)){
            for(NFAState fromNFA : state.getTo('e')){
                if(!this.closureSet.contains(fromNFA)) {
                    this.closureSet.add(fromNFA);
                    eClosure(fromNFA);
                }
            }
        }
        else{
            this.closureSet.add(state);
        }
//        what you can reach with "e"
//        set of states
        return this.closureSet;
    }
//
//    public Set<NFAState> DFS(NFAState state, Set<NFAState> closure){
//        Set<NFAState> ret = new LinkedHashSet<>();
//        closureSet = closure;
//        ret.add(state);
//        return getDFS(closureSet,state,ret);
//    }
//
//    private Set<NFAState> getDFS(Set<NFAState> visitedStates, NFAState state, Set<NFAState> ret) {
//        if(!state.getTo('e').isEmpty() && !visitedStates.contains(state)){
//            visitedStates.add(state);
//            for(NFAState fromNFA : state.getTo('e')){
//                ret.addAll(DFS(fromNFA, visitedStates));
//            }
//        }
//        return ret;
//    }


    /**
     * Adds a final state to the NFA
     *
     * @param name is the label of the state
     */
    @Override
    public void addFinalState(String name) {
        NFAState state = new NFAState(name,true);
//        if( state == null){
//            state = new NFAState(name, true);
//        if(states.contains(name)){
//
//        }
            addState(state);
//        } else {
//            System.out.println("WARNING: A state with name " + name + " already exists in the NFA");
//        }
    }

    /**
     * Adds the initial state to the NFA instance
     *
     * @param name is the label of the start state
     */
    @Override
    public void addStartState(String name) {
//        NFAState state = checkIfExists(name);
//        start = checkIfExists(name);
        start = new NFAState(name);
        if(start != null){
            //check
            if(states.contains(start)){
//                state = new NFAState(name);
                addState(start);
            }
            else{
//            this.start = state;
                addState(start);
            }
        }
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
     * Adds the transition to the NFA's delta data structure
     *
     * @param fromState is the label of the state where the transition starts
     * @param onSymb    is the symbol from the NFA's alphabet.
     * @param toState   is the label of the state where the transition ends
     */
    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        checkIfExists(fromState).addTransition(onSymb,checkIfExists(toState));
        if(!sigma.contains(onSymb) && onSymb != 'e'){
            sigma.add(onSymb);
        }
    }

    private NFAState checkIfExists(String name) {
        NFAState ret = null;
        for(NFAState stateL : this.states){
            if(stateL.getName().equals(name)){
                ret = stateL;
                break;
            }
        }
        return ret;
    }

//    public NFAState getState(String name){
//        NFAState ret = null;
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
        return this.start;
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
