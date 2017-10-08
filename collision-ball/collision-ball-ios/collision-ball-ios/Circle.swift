//
//  Circle.swift
//  collision-ball-ios
//
//  Created by Jacob Su on 10/5/17.
//  Copyright © 2017 Jacob Su. All rights reserved.
//

import Foundation

struct Circle {
    var x : Int
    var y : Int
    var r : Int
    var vx : Int
    var vy : Int
    var isFilled : Bool = false
    
    mutating func move(minx : Int, miny : Int, maxx : Int, maxy : Int) {
        x += vx
        y += vy
    
        checkCollision(minx: minx, miny: miny, maxx: maxx, maxy: maxy)
    }
    
    func contain(px : CFloat, py : CFloat) -> Bool {
        return ((CFloat(x) - px) * (CFloat(x) - px) + (CFloat(y) - py) * (CFloat(y) - py)) <= CFloat(r * r)
    }
    
    private mutating func checkCollision(minx : Int, miny : Int, maxx : Int, maxy : Int) {
        if (x - r < minx) {
            x = r
            vx = -vx;
        }
        
        if (x + r >= maxx) {
            x = maxx - r
            vx = -vx
        }
        
        if (y - r < miny) {
            y = r
            vy = -vy
        }
        
        if (y + r >= maxy) {
            y = maxy - r
            vy = -vy
        }
    }
}
