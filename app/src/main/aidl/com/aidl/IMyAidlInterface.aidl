// IMyAidlInterface.aidl
package com.aidl;

// Declare any non-default types here with import statements
import com.aidl.Person;
interface IMyAidlInterface {

      int add(int mum1,int mum2);
      List<Person> addPerson(in Person person);

}
