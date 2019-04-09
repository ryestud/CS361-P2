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
        this.delta = new HashMap<Character, HashSet<NFAState>>();
        this.name = name;
    }

    /**
     * Overlaoded constructor that sets the state type
     * @param name the state name
     * @param isFinal the type of state: true - final, false - nonfinal.
     */
    public NFAState(String name, boolean isFinal){
        this.delta = new HashMap<Character, HashSet<NFAState>>();
        this.name = name;
        this.isFinal = isFinal;
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
        HashSet<NFAState> transitionSymbol = delta.get(onSymb);
        if(transitionSymbol == null){
            transitionSymbol = new HashSet<NFAState>();
        }
        transitionSymbol.add(toState);
        delta.put(onSymb, transitionSymbol);
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
            return delta.get(symb);
        }
    }

}
