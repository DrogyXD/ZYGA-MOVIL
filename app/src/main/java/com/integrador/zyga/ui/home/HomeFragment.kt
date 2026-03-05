package com.integrador.zyga.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.integrador.zyga.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnMore = view.findViewById<FloatingActionButton>(R.id.btnMore)
        val moreOptions = view.findViewById<View>(R.id.layoutMoreOptions)

        btnMore.setOnClickListener {
            // Animación expand/collapse dentro del contenedor padre
            val parent = moreOptions.parent as ViewGroup
            TransitionManager.beginDelayedTransition(parent, AutoTransition())

            isExpanded = !isExpanded
            moreOptions.visibility = if (isExpanded) View.VISIBLE else View.GONE

            btnMore.animate()
                .rotation(if (isExpanded) 180f else 0f)
                .setDuration(180)
                .start()

            btnMore.setImageResource(
                if (isExpanded) R.drawable.ic_expand
                else R.drawable.ic_expand
            )

        }

        // Opcional: acciones al tocar cada opción
        view.findViewById<View>(R.id.optBattery)?.setOnClickListener {
            // TODO: Acción Pago de corriente
        }

        view.findViewById<View>(R.id.optLocksmith)?.setOnClickListener {
            // TODO: Acción Cerrajería
        }

        view.findViewById<View>(R.id.optRefill)?.setOnClickListener {
            // TODO: Acción Reabastecimiento
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}