package fa.nfa;

import fa.State;


import java.util.HashMap;
import java.util.HashSet;


public class NFAState extends State{

    private HashMap<Character,HashSet<NFAState>> delta;//delta
    private boolean isFinal;//remembers its type

    /**
     * Default constructor
     * @param name the state name
     */
    public NFAState(String name){
        initDefault(name);
        isFinal = false;
    }

    /**
     * Overlaoded constructor that sets the state type
     * @param name the state name
     * @param isFinal the type of state: true - final, false - nonfinal.
     */
    public NFAState(String name, boolean isFinal){
        initDefault(name);
        this.isFinal = isFinal;
    }

    private void initDefault(String name ){
        this.name = name;
        this.delta = new HashMap<Character, HashSet<NFAState>>();
    }

    /**
     * Accessor for the state type
     * @return true if final and false otherwise
     */
    public boolean isFinal(){
        return this.isFinal;
    }


    /**
     * Add the transition from <code> this </code> object
     * @param onSymb the alphabet symbol
     * @param toState to DFA state
     */
    public void addTransition(char onSymb, NFAState toState){
        HashSet<NFAState>
        delta.put(onSymb, toState);
    }

    /**
     * Retrieves the state that <code>this</code> transitions to
     * on the given symbol
     * @param symb - the alphabet symbol
     * @return the new state
     */
    public HashSet<NFAState> getTo(char symb){
        HashSet<NFAState> ret = delta.get(symb);
        if(ret == null){
            return new HashSet<NFAState>();
        }
        else{
            return ret;
        }
    }

}
