using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Winner : MonoBehaviour {
    
    Text text;

    // Use this for initialization
    void Start()
    {
        text = GetComponent<Text>();
    }

    // Update is called once per frame
    void Update()
    {
        if (BlueScore.score == 5) { 
            text.color = Color.blue;
             text.text = "Blue Wins";
        }

        if (RedScore.score == 5)
        {
            text.color = Color.red;
            text.text = "Red Wins";
        }
    }
}
