package com.example.compose_demo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _phoneNumList = MutableLiveData<List<PhoneNum>>(emptyList())
    val phoneNumList: LiveData<List<PhoneNum>> = _phoneNumList

    private val _phoneNum = MutableLiveData(PhoneNum())
    val phoneNum: LiveData<PhoneNum> = _phoneNum

    private val _searchPNList = MutableLiveData<List<PhoneNum>>(emptyList())
    val searchPNList: LiveData<List<PhoneNum>> = _searchPNList

    /* 从asset读取联系人数据 */
    fun readPhoneNumBook(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val reader = context.assets.open("phone-list.json").reader()
            val type = object : TypeToken<List<PhoneNum>>() {}.type
            val data: List<PhoneNum> = Gson().fromJson(reader, type)
            Log.d("phone-list", data.toString())
            _phoneNumList.postValue(data)
        }
    }

    fun changePhoneNum(data: PhoneNum) {
        _phoneNum.value = data
    }

    fun findPhoneNumList(input: String) {
        Log.d("findPhoneNumList", "invoke")
        viewModelScope.launch(Dispatchers.IO) {
            if (input.isBlank() || input.isEmpty()) {
                _searchPNList.postValue(emptyList())
            } else {
                val list =
                    _phoneNumList.value!!.filter {
                        it.name.indexOf(input) != -1 || it.abb.indexOf(
                            input
                        ) != -1
                    }
                Log.d("findPhoneNumList", "list.size=${list.size}")
                _searchPNList.postValue(list)
            }
        }
    }

    fun cleanSearch() {
        _searchPNList.value = emptyList()
    }
}

data class PhoneNum(val name: String = "", val phone_num: String = "", val abb: String = "")