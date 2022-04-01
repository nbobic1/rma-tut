package com.tut.rma

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "Broadcast"

class Brodcast : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if(intent.toUri(Intent.URI_INTENT_SCHEME).toString().contains("noConnectivity=true"))
            StringBuilder().apply {
                append("Nema interneta")
                toString().also { log ->
                    Log.d(TAG, log)
                    Toast.makeText(context, log, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
