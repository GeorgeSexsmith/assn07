package assn07;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "YOUR PASSWORD HERE";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        int hash = (key.hashCode())% 50;
        Account obj = _passwords[hash];
         if (_passwords[hash] == null) {
             _passwords[hash] = new Account(key, value);
         }
         else {
            if(obj.getWebsite().equals(key)){
                obj.setPassword(value);
            } else {
                while(obj.getNext() != null) {
                    if(obj.getNext().equals(key)) {
                        obj.getNext().setPassword(value);
                        return;
                    } else {
                        obj = obj.getNext();
                    }
                }
                obj.setNext(new Account(key,value));
            }
         }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int hash = (key.hashCode())% 50;
        Account obj = _passwords[hash];
        if(obj.getWebsite() == key) {
            return (V)obj.getPassword();
        }
        while( obj.getNext() != null) {
            if (obj.getWebsite() == key) {
                return (V)obj.getPassword();
            }
            obj = obj.getNext();
        }
        if(obj.getWebsite() != key) {
            return null;
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int size = 0;
        for(int i = 0; i < 50; i++) {
            Account obj = _passwords[i];
            if(obj.getWebsite() != null && obj.getPassword() != null) {
                size += 1;
            }
            while(obj.getNext() != null) {
                obj = obj.getNext();
                size += 1;
            }
        }
        return size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        return null;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicates(V value) {
        return null;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return false;
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        // TODO:
        
        // int leftLimit = ; // hint: numeral '0'=48
        // int rightLimit = ; // hint: letter 'z'=122
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4
        int leftLimit = 0;
        int rightLimit = 0;
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
