using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayAgain : MonoBehaviour {

    public void changeToMenu()
    {
        RedScore.score = 0;
        BlueScore.score = 0;
        string scene = PlayerPrefs.GetString("lastloadedScene");
        SceneManager.LoadScene(scene);
    }
}
