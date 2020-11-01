package DoorDash;
/*
 * In the Simple Database problem, you'll implement an in-memory database similar to Redis. For simplicity's sake, instead of dealing with 
 * multiple clients and communicating over the network, your program will receive commands via standard input (stdin), and should write 
 * appropriate responses to standard output (stdout).
 * 
 * 
 * SET name value 每 Set the variable name to the value value. Neither variable names nor values will contain spaces. 
 * GET name 每 Print out the value of the variable name, or NULL if that variable is not set. 
 * UNSET name 每 Unset the variable name, making it just like that variable was never set. 
 * END 每 Exit the program. 
 * 
 * 
    INPUT        OUTPUT
    SET ex 10   
    GET ex       10    
    UNSET ex
    GET ex       NULL
    END

 * Transaction Commands In addition to the above data commands, your program should also support database transactions by also implementing these commands:
 * BEGIN 每 Open a new transaction block. Transaction blocks can be nested; a BEGIN can be issued inside of an existing block. 
 * ROLLBACK 每 Undo all of the commands issues in the most recent transaction block, and close the block. Print nothing if successful,
 *    or print NO TRANSACTION if no transaction is in progress. 
 * COMMIT 每 Close all open transaction blocks, permanently applying the changes made in them. Print nothing if successful, 
 *    or print NO TRANSACTION if no transaction is in progress. Any data command that is run outside of a transaction block should commit immediately.
 *
 * Here are some example command sequences:

    INPUT     OUTPUT
    BEGIN
    SET a 10
    GET a       10
    BEGIN
    SET a 20
    GET a       20
    ROLLBACK
    GET a       10
    ROLLBACK
    GET a       NULL
    END
 */
import java.util.*;
import java.io.*;
public class MemoryDatabase {
    static class DataSnapshot {
        public Map<String, String> _dataMap;
        public Set<String> _removedKeys;
        
        public DataSnapshot(boolean base) {
            _dataMap = new HashMap<>();
            if (!base)
                _removedKeys = new HashSet<>();
            else
                _removedKeys = null;
        }
        
        public boolean isKeyRemoved(String key) {
            if (_removedKeys != null && _removedKeys.contains(key)) {
                return true;
            }
            return false;
        }
        
        public String get(String key) {
            return _dataMap.get(key);
        }
        
        public void set(String key, String value) {
            if (_removedKeys != null) {
                _removedKeys.remove(key);
            }
            _dataMap.put(key, value);
        }
        
        public void unset(String key) {
            if (_removedKeys != null) {
                _removedKeys.add(key);
            }
            _dataMap.remove(key);
        }
        
        public void merge(DataSnapshot snapshot) {
            _dataMap.putAll(snapshot._dataMap);
            for (String key : snapshot._removedKeys)
                _dataMap.remove(key);
        }
    }
    enum CommandEnum {
        GET,
        SET,
        UNSET,
        ROLLBACK,
        BEGIN,
        COMMIT,
        END
    }
    
    static class Database {
        private List<DataSnapshot> _transactions;
        
        public Database() {
            DataSnapshot base = new DataSnapshot(true);
            _transactions = new ArrayList<>();
            _transactions.add(base);
        }
        
        public String get(String key) {
            String result = null;
            for (int i = _transactions.size() - 1; i >= 0 ; i--) {
                DataSnapshot snapshot = _transactions.get(i);
                if (snapshot.isKeyRemoved(key))
                    return null;
                result = snapshot.get(key);
                if (result != null)
                    return result;
            }
            return result;
        }
        
        public void set(String key, String value) {
            _transactions.get(_transactions.size() - 1).set(key, value);
        }
        
        public void unset(String key) {
            _transactions.get(_transactions.size() - 1).unset(key);
        }
        
        public int begin() {
            _transactions.add(new DataSnapshot(false));
            return _transactions.size();
        }
        
        public void commit() {
            DataSnapshot base = _transactions.get(0);
            for (int i = 1; i < _transactions.size(); i++) {
                base.merge(_transactions.get(i));
            }
            
            _transactions.clear();
            _transactions.add(base);
        }
        
        public int rollback() {
            if (_transactions.size() < 2)
                return -1;
            int size = _transactions.size();
            _transactions.remove(size - 1);
            return size;
        }
        
        public void executeCommand(String raw) {
            String args[] = raw.split(" ");
            CommandEnum command;
            try {
                command = CommandEnum.valueOf(args[0].toUpperCase());
            } catch (Exception e) {
                System.out.println("Invalid command: " + args[0]);
                return;
            }
            
            switch (command) {
            case GET:
                if (args.length < 2) {
                    System.out.println("Missing key in get command. ");
                    return;
                }
                String result = get(args[1]);
                result = result == null ? "NULL" : result;
                System.out.println(result);
                break;
            case SET:
                if (args.length < 3) {
                    System.out.println("Missing key or value in set command. ");
                    return;
                }
                set(args[1], args[2]);
                break;
            case UNSET:
                if (args.length < 2) {
                    System.out.println("Missing key in unset command. ");
                    return;
                }
                unset(args[1]);
                break;
            case BEGIN:
                int idx = begin();
                System.out.println("transaction " + idx + " start here.");
                break;
            case ROLLBACK:
                int r = rollback();
                System.out.println("transaction " + r + " is rolled back.");
                break;
            case COMMIT:
                commit();
                System.out.println("transaction(s) are committed.");
                break;
            default:
                System.exit(0);
                
            }
        }
        
    }
    
    public static void main(String args[]) throws IOException {
        Database database = new Database();
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String rawCommand = bufferedReader.readLine();
            rawCommand = rawCommand.trim();
            database.executeCommand(rawCommand);
        }
    }

}
